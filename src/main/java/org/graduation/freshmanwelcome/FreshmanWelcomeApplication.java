package org.graduation.freshmanwelcome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@MapperScan("org.graduation.freshmanwelcome.mapper")
@EntityScan("org.graduation.freshmanwelcome.entity")
@SpringBootApplication
public class FreshmanWelcomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FreshmanWelcomeApplication.class, args);
    }

}
