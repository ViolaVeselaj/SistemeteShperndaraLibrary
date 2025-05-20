package com.example.sistemeteshperndara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class SistemeTeShperndaraApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemeTeShperndaraApplication.class, args);
    }

}
