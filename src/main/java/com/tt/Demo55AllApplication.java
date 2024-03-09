package com.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Demo55AllApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo55AllApplication.class, args);
    }

}
