package pro.sky.certificationcareercenter.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Класс-сущность учета носков на складе магазина
 */
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "socks")
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "color")
    private String color;
    @Column(name = "cotton_part")
    @Min(0)
    @Max(100)
    private Integer cottonPart;
    @Column(name = "quantity")
    @Min(1)
    private Integer quantity;

    public Socks(String color, Integer cottonPart, Integer quantity) {
        this.color = color;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Идентификатор: " + id + ". Цвет носков: " + color + ". Процентное содержание хлопка в составе носков: " + cottonPart + ". Колличество пар носков: " + quantity;
    }
}
