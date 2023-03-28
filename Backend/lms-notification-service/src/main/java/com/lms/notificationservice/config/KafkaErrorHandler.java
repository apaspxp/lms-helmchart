package com.lms.notificationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.net.SocketTimeoutException;

@Configuration
@EnableKafka
public class KafkaErrorHandler {

    @Bean
    public static DefaultErrorHandler errorHandler() {
        BackOff fixedBackOff = new FixedBackOff(1000L, 0);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
            System.out.println("heyyyyy");
            // logic to execute when all the retry attempts are exhausted
            //send all failed emails to failed topic

        }, fixedBackOff);
        errorHandler.addRetryableExceptions(SocketTimeoutException.class);
        errorHandler.addNotRetryableExceptions(NullPointerException.class);
        return errorHandler;
    }

}
