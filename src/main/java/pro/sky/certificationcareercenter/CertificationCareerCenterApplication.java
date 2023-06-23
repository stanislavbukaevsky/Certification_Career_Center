package pro.sky.certificationcareercenter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс во всём приложении. Служит для запуска приложения
 */
@SpringBootApplication
@OpenAPIDefinition
public class CertificationCareerCenterApplication {
	/**
	 * Этот метод запускает всё приложение
	 *
	 * @param args массив строк
	 */
	public static void main(String[] args) {
		SpringApplication.run(CertificationCareerCenterApplication.class, args);
	}

}
