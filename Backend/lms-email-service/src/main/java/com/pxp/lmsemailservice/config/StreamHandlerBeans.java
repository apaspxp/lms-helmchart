package com.pxp.lmsemailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class StreamHandlerBeans {

    @Bean
    public Consumer<String> testConsumer(){
        return str -> System.out.println(str);
    }
}
