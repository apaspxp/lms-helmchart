package com.pxp.lmsleaveservice.utility;

import com.pxp.lmsleaveservice.requests.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailUtility {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${topic.email}")
    String kafkaTopic;

    public void sendEmail(String from, String to, String subject, String body){
        try {
            log.info("Sending email message to kafka topic : {}", kafkaTopic);
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setFrom(from);
            emailRequest.setTo(to);
            emailRequest.setSubject(subject);
            emailRequest.setBody(body);
            emailRequest.setAttachment("");
            kafkaTemplate.send(kafkaTopic, emailRequest);
            log.info("Email topic sent to kafka successfully");
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

}
