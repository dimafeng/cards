package com.dimafeng.cards;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.dimafeng.cards")
//@EnableWebMvc
@EnableMongoRepositories("com.dimafeng.cards.repository")
public class Application {

    @Value("${mongo.db.name:cards}")
    private String databaseName;

    @Value("${mongo.db.server}")
    private String databaseServer;

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(databaseServer);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), databaseName);
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setTomcatContextCustomizers(Arrays.asList(new CustomCustomizer()));
        return factory;
    }

    static class CustomCustomizer implements TomcatContextCustomizer {
        @Override
        public void customize(Context context) {
            context.setUseHttpOnly(false);
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
