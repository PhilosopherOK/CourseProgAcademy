package academy.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Домашка на основі проєкту HappyURL:

1) Доробити код, щоб замість ID кругом повертався повний скорочений урл
2) Зробити скороченим посиланням TTL = Time to live, щоб вони через певний
 час переставали працювати. TTL в хвилинах задається при створенні посилання клієнтом.
3) Зробити DELETE endpoint для видалення існуючих посилань.
 */
@SpringBootApplication
public class HappyUrlUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyUrlUpdateApplication.class, args);
	}

}
