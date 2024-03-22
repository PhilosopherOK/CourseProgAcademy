package ua.kiev.prog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/*
дз11
1) Доробити метод search в контролері   👍
2) Зробити можливість видалення всієї обранцуої групи з усіма контактами.
Додаткове складне дз:
3) Зробити можливість завантажити на сервер і додати список контактів в XML форматі.

дз12
ДЗ на основі прикладу Contant list:
Зробити ф-ю переміщення вибраного кліента(ів) в іншу групу.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //...
        SpringApplication.run(Application.class, args);
    }
}
