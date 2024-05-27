package com.example.hw17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
ДЗ:

1) Зробити веб додаток на Spring Boot (можна візуально як Contact List),
який дозволить завантажувати фото на сервер та виводитиме їх як каталог з пегінацією по 5 шт.

2) Зробити можливість групувати фото в альбоми з назвами.
 */
@SpringBootApplication
public class Hw17Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw17Application.class, args);
	}

}
