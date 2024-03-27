package ua.kiev.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringGeo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringGeo1Application.class, args);
	}

}
