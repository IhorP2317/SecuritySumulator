package com.securitySimulator;

import com.securitySimulator.repository.UserRepository;
import com.securitySimulator.seeder.DataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;


//http://localhost:8080/swagger-ui/index.html
@SpringBootApplication
@EnableJpaRepositories("com.securitySimulator.repository")
@ComponentScan(basePackages = { "com.*" })
@EntityScan("com.securitySimulator.model.*")
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}