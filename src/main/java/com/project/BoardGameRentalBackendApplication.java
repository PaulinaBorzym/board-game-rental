package com.project;

import com.project.service.CurrencyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class BoardGameRentalBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardGameRentalBackendApplication.class, args);

    }

}
