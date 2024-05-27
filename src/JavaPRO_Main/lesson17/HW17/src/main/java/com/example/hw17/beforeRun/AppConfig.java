package com.example.hw17.beforeRun;

import com.example.hw17.models.Album;
import com.example.hw17.services.AlbumService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {
    @Bean
    public CommandLineRunner demo(AlbumService albumService
                                  ) {
        return args -> {
            if (albumService.findMain() == null) {
                albumService.save(new Album("MAIN"));
                }
            };
        }
    }
