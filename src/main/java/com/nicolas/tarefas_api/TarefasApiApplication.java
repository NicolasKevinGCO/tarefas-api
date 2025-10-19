package com.nicolas.tarefas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class TarefasApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TarefasApiApplication.class, args);
    }

    @Bean
    public ApplicationRunner mappingsLogger(RequestMappingHandlerMapping mapping) {
        return args -> {
            System.out.println("===== REQUEST MAPPINGS =====");
            mapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) ->
                System.out.println(requestMappingInfo + " -> " + handlerMethod.getMethod())
            );
            System.out.println("============================");
        };
    }
}
