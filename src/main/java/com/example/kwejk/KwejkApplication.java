package com.example.kwejk;

import com.example.kwejk.upload.StorageProperties;
import com.example.kwejk.upload.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
    @EnableConfigurationProperties(StorageProperties.class)
    public class KwejkApplication {

        public static void main(String[] args) {
            SpringApplication.run(KwejkApplication.class, args);
        }

        @Bean
        CommandLineRunner init(StorageService storageService) {
            return (args) -> {
                storageService.deleteAll();
                storageService.init();
            };
        }
    }


