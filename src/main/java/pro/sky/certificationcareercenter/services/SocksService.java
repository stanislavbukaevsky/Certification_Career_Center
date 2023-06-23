package pro.sky.certificationcareercenter.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.certificationcareercenter.dto.PostSocksDTO;
import pro.sky.certificationcareercenter.entities.Socks;
import pro.sky.certificationcareercenter.enums.Operation;
import pro.sky.certificationcareercenter.exceptions.SocksNotFoundException;
import pro.sky.certificationcareercenter.mappers.SocksMapper;
import pro.sky.certificationcareercenter.repositories.SocksRepository;

import static pro.sky.certificationcareercenter.constants.ExceptionConstant.RESPONSE_STATUS_SOCKS_EXCEPTION;
import static pro.sky.certificationcareercenter.constants.ExceptionConstant.RESPONSE_STATUS_SOCKS_EXCEPTION_2;
import static pro.sky.certificationcareercenter.constants.LoggerConstant.*;

/**
 * Сервис-класс для работы с методами учета носков на складе магазина
 */
@Service
@RequiredArgsConstructor
@Transactional
public class SocksService {
    private final Logger logger = LoggerFactory.getLogger(SocksService.class);
    private final SocksMapper socksMapper;
    private final SocksRepository socksRepository;

    /**
     * Этот метод для добавления носков на склад магазина
     *
     * @param postSocksDTO DTO добавляемых носков
     * @return Возвращает носки, добавленные на склад магазина
     */
    public PostSocksDTO addSocks(PostSocksDTO postSocksDTO) {
        logger.info(ADD_SOCKS_SERVICE_LOGGER, postSocksDTO);
        Socks socks = socksRepository.findSocksByColorAndCottonPart(postSocksDTO.getColor(), postSocksDTO.getCottonPart())
                .orElse(new Socks(postSocksDTO.getColor(), postSocksDTO.getCottonPart(), 0));
        if (socks.getCottonPart() < 0 && socks.getCottonPart() > 100 && socks.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RESPONSE_STATUS_SOCKS_EXCEPTION);
        }
        socks.setQuantity(socks.getQuantity() + postSocksDTO.getQuantity());
        Socks result = socksRepository.save(socks);
        return socksMapper.importEntityToPostDTO(result);
    }

    /**
     * Этот метод для удаления/изменения носков со склада магазина
     *
     * @param postSocksDTO DTO удаляемых носков
     * @return Возвращает носки, которые были изменены на складе магазина
     */
    public PostSocksDTO dropSocks(PostSocksDTO postSocksDTO) {
        logger.info(DROP_SOCKS_SERVICE_LOGGER, postSocksDTO);
        Socks socks = socksRepository.findSocksByColorAndCottonPart(postSocksDTO.getColor(), postSocksDTO.getCottonPart())
                .orElseThrow(() -> new SocksNotFoundException(RESPONSE_STATUS_SOCKS_EXCEPTION_2));
        socks.setQuantity(socks.getQuantity() - postSocksDTO.getQuantity());
        if (socks.getQuantity() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RESPONSE_STATUS_SOCKS_EXCEPTION);
        }
        Socks result = socksRepository.save(socks);
        return socksMapper.importEntityToPostDTO(result);
    }

    /**
     * Этот метод возвращает общее количество носков на складе, соответствующих переданным параметрам запроса
     *
     * @param color      цвет носков
     * @param cottonPart количество хлопка в составе носков
     * @param operation  оператор сравнения значения количества хлопка в составе носков
     * @return Возвращает общее количество носков, соответствующих переданным параметрам запроса,
     * либо выбрасывает исключение {@link ResponseStatusException}, если неверный запрос
     */
    public Integer getSocks(String color, Integer cottonPart, Operation operation) {
        Integer socksMoreThan = socksRepository.findSocksByColorAndCottonPartGreaterThan(color, cottonPart);
        Integer socksLessThan = socksRepository.findSocksByColorAndCottonPartLessThan(color, cottonPart);
        Integer socksEqual = socksRepository.findSocksByColorAndCottonPartEquals(color, cottonPart);

        if (operation.equals(Operation.MORE_THAN)) {
            logger.info(GET_SOCKS_MORE_THAN_SERVICE_LOGGER, color, cottonPart, operation);
            return socksMoreThan;
        } else if (operation.equals(Operation.LESS_THAN)) {
            logger.info(GET_SOCKS_LESS_THAN_SERVICE_LOGGER, color, cottonPart, operation);
            return socksLessThan;
        } else if (operation.equals(Operation.EQUAL)) {
            logger.info(GET_SOCKS_EQUAL_SERVICE_LOGGER, color, cottonPart, operation);
            return socksEqual;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, RESPONSE_STATUS_SOCKS_EXCEPTION_2);
    }

}
