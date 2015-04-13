package com.dimafeng.cards;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.dimafeng.cards")
//@EnableWebMvc
@EnableMongoRepositories("com.dimafeng.cards.repository")
public class Application {
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost:27017");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "cards");
    }


    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
