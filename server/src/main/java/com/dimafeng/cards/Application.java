package com.dimafeng.cards;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.dimafeng.cards")
@EnableWebMvc
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
