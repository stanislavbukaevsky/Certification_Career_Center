package pro.sky.certificationcareercenter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.certificationcareercenter.dto.PostSocksDTO;
import pro.sky.certificationcareercenter.services.SocksService;

import javax.validation.Valid;

import static pro.sky.certificationcareercenter.constants.FrontLinkConstant.*;
import static pro.sky.certificationcareercenter.constants.LoggerConstant.*;

/**
 * Класс-контроллер для работы с методами учета носков на складе магазина
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(REQUEST_MAPPING_FRONT_LINK)
@Tag(name = "Работа со всеми методами по учету носков на складе магазина", description = "Позволяет управлять методами по учету носков на складе магазина")
public class SocksController {
    private final Logger logger = LoggerFactory.getLogger(SocksController.class);
    private final SocksService socksService;

    /**
     * Этот метод для добавления носков на склад магазина
     *
     * @param postSocksDTO DTO добавляемых носков
     * @return Возвращает носки, добавленные на склад магазина
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось добавить приход", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostSocksDTO.class))),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, независящая от вызывающей стороны (например, база данных недоступна)")
    })
    @Operation(summary = "Добавить носки на склад")
    @PostMapping(POST_MAPPING_ADD_FRONT_LINK)
    public ResponseEntity<PostSocksDTO> addSocks(@Valid @RequestBody PostSocksDTO postSocksDTO) {
        logger.info(ADD_SOCKS_CONTROLLER_LOGGER, postSocksDTO);
        return ResponseEntity.ok(socksService.addSocks(postSocksDTO));
    }

    /**
     * Этот метод для удаления/изменения носков со склада магазина
     *
     * @param postSocksDTO DTO удаляемых носков
     * @return Возвращает носки, которые были изменены на складе магазина
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Удалось изменить приход", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PostSocksDTO.class))),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, независящая от вызывающей стороны (например, база данных недоступна)")
    })
    @Operation(summary = "Удалить носки со склада")
    @PostMapping(POST_MAPPING_DROP_FRONT_LINK)
    public ResponseEntity<PostSocksDTO> dropSocks(@Valid @RequestBody PostSocksDTO postSocksDTO) {
        logger.info(DROP_SOCKS_CONTROLLER_LOGGER, postSocksDTO);
        return ResponseEntity.ok(socksService.dropSocks(postSocksDTO));
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа"),
            @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат"),
            @ApiResponse(responseCode = "500", description = "Произошла ошибка, независящая от вызывающей стороны (например, база данных недоступна)")
    })
    @Operation(summary = "Показать общее количество носков по переданным параметрам")
    @GetMapping
    public ResponseEntity<Integer> getSocks(@RequestParam @Parameter(description = "Цвет носков (игнорируя регистр)") String color,
                                            @RequestParam @Parameter(description = "Количество хлопка (целое число от 0 до 100 включительно)") @Valid Integer cottonPart,
                                            @RequestParam @Parameter(description = "Оператор сравнения значения: MORE_THAN - больше, чем. LESS_THAN - меньше, чем. EQUAL - равное") pro.sky.certificationcareercenter.enums.Operation operation) {
        logger.info(GET_SOCKS_CONTROLLER_LOGGER, color, cottonPart, operation);
        return ResponseEntity.ok(socksService.getSocks(color, cottonPart, operation));
    }
}
