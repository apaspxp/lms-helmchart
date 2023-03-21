package com.lms.notificationservice.consumer;

import com.google.gson.Gson;
import com.lms.notificationservice.requests.EmailRequest;
import com.lms.notificationservice.requests.RetryRequest;
import com.lms.notificationservice.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Slf4j
@Component
public class EmailConsumer {

    @Autowired
    IEmailService emailService;

    @KafkaListener(topics = "${topic.email}", containerFactory = "emailRequestKafkaListenerFactory")
    public void consumeJson(EmailRequest emailRequest) {
        log.info("Message Received : {}", emailRequest);
        emailService.sendMail(emailRequest);
    }

//    @KafkaListener(topics = "${topic.retryEmail}", containerFactory = "retryEmailRequestKafkaListenerFactory")
//    public void consumeJson(RetryRequest retryRequest) {
//        log.info("Message Received : {}", new Gson().toJson(retryRequest));
//    }

    @RetryableTopic(
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            attempts = "1",
            autoCreateTopics = "false",
            include = SocketTimeoutException.class
    )
    @KafkaListener(topics = "${topic.retryEmail}")
    public void consumeRetry(RetryRequest retryRequest) {
        log.info("Message Received : {}", new Gson().toJson(retryRequest));
    }

//    @KafkaListener(topics = "${topic.email}", containerFactory = "emailRequestKafkaListenerFactory")
//    @KafkaHandler(isDefault = true)
//    public void listenUnknownMessage(Object object) {
//        log.info("unknown message {}", new Gson().toJson(object));
//    }

//    @KafkaHandler
//    @RetryableTopic(
//      backoff = @Backoff(value = 5000L),
//      attempts = "1",
//      autoCreateTopics = "false",
//      include = SocketTimeoutException.class, exclude = NullPointerException.class
//    )
//    public void consumeJson(EmailRequest emailRequest) {
//        log.info("Message Received : {}", emailRequest);
//        emailService.sendMail(emailRequest);
//        if (emailRequest.getBody().startsWith("Sarthak")) {
//            throw new RuntimeException("failed");
//        }
//    }
}