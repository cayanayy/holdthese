package com.cayanay.holdthese;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HoldtheseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoldtheseApplication.class, args);
    }
}
