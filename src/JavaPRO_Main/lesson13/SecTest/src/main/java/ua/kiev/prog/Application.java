package ua.kiev.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
1) В прикладі Sectest
а) Додати роль Модератор, що зможе бачити адмінку read only
б) Додати можливість адмінку крім видалення блокувати та розблоковувати акаунти
 */
@SpringBootApplication
public class Application {
    public static void  main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}