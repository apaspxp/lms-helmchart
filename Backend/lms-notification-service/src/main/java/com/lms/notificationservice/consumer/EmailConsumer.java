package com.lms.notificationservice.consumer;

import com.google.gson.Gson;
import com.lms.notificationservice.requests.EmailRequest;
import com.lms.notificationservice.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@KafkaListener(topics = "${topic.email}", containerFactory = "emailRequestKafkaListenerFactory")
public class EmailConsumer {

    @Autowired
    IEmailService emailService;

    @KafkaHandler
    public void consumeJson(EmailRequest emailRequest) {
        log.info("Message Received : {}", emailRequest);
        emailService.sendMail(emailRequest);
        if (emailRequest.getBody().startsWith("Sarthak")) {
            throw new RuntimeException("failed");
        }
    }

    @KafkaHandler(isDefault = true)
    public void listenUnknownMessage(Object object) {
        log.info("unknown message {}", new Gson().toJson(object));
    }

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