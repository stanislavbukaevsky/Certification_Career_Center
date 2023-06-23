package pro.sky.certificationcareercenter.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Класс-DTO для регистрации прихода/расхода носков со склада магазина
 */
@Data
public class PostSocksDTO {
    private String color;
    @Min(0)
    @Max(100)
    private Integer cottonPart;
    @Min(1)
    private Integer quantity;
}
