package pro.sky.certificationcareercenter.mappers;

import org.mapstruct.Mapper;
import pro.sky.certificationcareercenter.dto.PostSocksDTO;
import pro.sky.certificationcareercenter.entities.Socks;

/**
 * Маппер-интерфейс, который преобразует сущность всех носков, которые присутствуют на складе магазина {@link Socks}
 * в DTO {@link PostSocksDTO}
 */
@Mapper
public interface SocksMapper {
    PostSocksDTO importEntityToPostDTO(Socks socks);
}
