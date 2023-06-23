package pro.sky.certificationcareercenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.sky.certificationcareercenter.entities.Socks;

import java.util.Optional;

/**
 * Интерфейс-репозиторий для работы с методами учета носков на складе магазина.
 * Наследуется от интерфейса {@link JpaRepository}. Параметры: <br>
 * {@link Socks} - класс-сущность <br>
 * {@link Long} - идентификатор <br>
 */
@Repository
public interface SocksRepository extends JpaRepository<Socks, Long> {
    /**
     * Этот метод ищет носки на складе по цвету и количеству хлопка
     *
     * @param color      цвет носков
     * @param cottonPart количество хлопка в составе носков
     * @return Возвращает найденные носки по цвету и количеству хлопка
     */
    Optional<Socks> findSocksByColorAndCottonPart(String color, Integer cottonPart);

    /**
     * Этот метод выдает общее количество носков на складе, больше, чем передано в параметрах пользователем. <br>
     * В этом методе используется аннотация {@link Query}, для детального поиска.
     * Также, используется оператор LOWER, для игнорирования регистра
     *
     * @param color      цвет носков
     * @param cottonPart количество хлопка в составе носков
     * @return Возвращает общее количество носков, соответствующих переданным параметрам запроса
     */
    @Query(value = "SELECT SUM(quantity) FROM socks WHERE LOWER(color) = LOWER(concat(:color)) AND cotton_part > :cottonPart", nativeQuery = true)
    Integer findSocksByColorAndCottonPartGreaterThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    /**
     * Этот метод выдает общее количество носков на складе, меньше, чем передано в параметрах пользователем. <br>
     * В этом методе используется аннотация {@link Query}, для детального поиска.
     * Также, используется оператор LOWER, для игнорирования регистра
     *
     * @param color      цвет носков
     * @param cottonPart количество хлопка в составе носков
     * @return Возвращает общее количество носков, соответствующих переданным параметрам запроса
     */
    @Query(value = "SELECT SUM(quantity) FROM socks WHERE LOWER(color) = LOWER(concat(:color)) AND cotton_part < :cottonPart", nativeQuery = true)
    Integer findSocksByColorAndCottonPartLessThan(@Param("color") String color, @Param("cottonPart") Integer cottonPart);

    /**
     * Этот метод выдает общее количество носков на складе, равное тому, сколько передано в параметрах пользователем. <br>
     * В этом методе используется аннотация {@link Query}, для детального поиска.
     * Также, используется оператор LOWER, для игнорирования регистра
     *
     * @param color      цвет носков
     * @param cottonPart количество хлопка в составе носков
     * @return Возвращает общее количество носков, соответствующих переданным параметрам запроса
     */
    @Query(value = "SELECT SUM(quantity) FROM socks WHERE LOWER(color) = LOWER(concat(:color)) AND cotton_part = :cottonPart", nativeQuery = true)
    Integer findSocksByColorAndCottonPartEquals(@Param("color") String color, @Param("cottonPart") Integer cottonPart);
}
