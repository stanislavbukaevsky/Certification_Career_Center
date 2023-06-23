package pro.sky.certificationcareercenter.exceptions;

import java.util.NoSuchElementException;

/**
 * Класс-исключение, если носки не найдены в базе данных. <br>
 * Наследуется от класса {@link NoSuchElementException}
 */
public class SocksNotFoundException extends NoSuchElementException {
    public SocksNotFoundException(String message) {
        super(message);
    }
}
