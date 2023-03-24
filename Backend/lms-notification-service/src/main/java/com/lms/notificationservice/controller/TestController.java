package com.lms.notificationservice.controller;

import com.lms.notificationservice.requests.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class TestController {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/notify")
    public String notified(@RequestParam("body") String body){
        EmailRequest e = new EmailRequest();
        e.setBody(body);
        e.setTo("dsf");
        e.setSubject("DSfs");
        e.setAttachment("SD");
        e.setFrom("sdf");
        kafkaTemplate.send("lms_email", e);
        return "notified";
    }

}
