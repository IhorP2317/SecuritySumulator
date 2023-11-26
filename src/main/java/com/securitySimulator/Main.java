package com.securitySimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;



//http://localhost:8080/swagger-ui/index.html
@SpringBootApplication
@EnableJpaRepositories("com.securitySimulator.repository")
@ComponentScan(basePackages = { "com.*" })
@EntityScan("com.securitySimulator.model.*")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}