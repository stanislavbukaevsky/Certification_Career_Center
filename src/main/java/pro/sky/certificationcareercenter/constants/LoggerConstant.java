package pro.sky.certificationcareercenter.constants;

/**
 * Этот класс содержит текстовые константные переменные для всех логов в приложении
 */
public class LoggerConstant {
    public static final String ADD_SOCKS_SERVICE_LOGGER = "Вызван метод добавления носков в базу данных в сервисе. Детали по товару: {}";
    public static final String ADD_SOCKS_CONTROLLER_LOGGER = "Вызван метод добавления носков в базу данных в контроллере. Детали по товару: {}";
    public static final String DROP_SOCKS_SERVICE_LOGGER = "Вызван метод удаления носков из базы данных в сервисе. Детали по товару: {}";
    public static final String DROP_SOCKS_CONTROLLER_LOGGER = "Вызван метод удаления носков из базы данных в контроллере. Детали по товару: {}";
    public static final String GET_SOCKS_MORE_THAN_SERVICE_LOGGER = "Вызван метод возвращения общего количества носков на складе, больше, чем передано в параметрах пользователем в сервисе. Цвет носков: {}. Количество хлопка: {}. Операция: {}";
    public static final String GET_SOCKS_LESS_THAN_SERVICE_LOGGER = "Вызван метод возвращения общего количества носков на складе, меньше, чем передано в параметрах пользователем в сервисе. Цвет носков: {}. Количество хлопка: {}. Операция: {}";
    public static final String GET_SOCKS_EQUAL_SERVICE_LOGGER = "Вызван метод возвращения общего количества носков на складе, равное тому, сколько передано в параметрах пользователем в сервисе. Цвет носков: {}. Количество хлопка: {}. Операция: {}";
    public static final String GET_SOCKS_CONTROLLER_LOGGER = "Вызван метод возвращения общего количества носков на складе в контроллере. Цвет носков: {}. Количество хлопка: {}. Операция: {}";
}
