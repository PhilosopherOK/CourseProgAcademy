package ua.kiev.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
1) Доробити бота так, щоб при розсилках використовувався pagination
2) Додати простий веб інтерфейс, що повідомлятиме кількість
зареєстраваних користувачів у боті (бажано по аналогії з прикладом про курс евро)
3) Зробити так, щоб інтерфейс був доступний лише адміну (spring sec)
 */
@SpringBootApplication
@EnableScheduling
public class TeleBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeleBotApplication.class, args);
    }

}
