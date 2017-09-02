package com.redhat.developers.osio.microspringboot2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class Microspringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(Microspringboot2Application.class, args);
    }

    @org.springframework.context.annotation.Configuration
    public class Configuration {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
