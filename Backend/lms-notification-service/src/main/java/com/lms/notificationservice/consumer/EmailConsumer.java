package com.lms.notificationservice.consumer;

import com.lms.notificationservice.requests.EmailRequest;
import com.lms.notificationservice.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.mail.MailException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmailConsumer {

    @Autowired
    IEmailService emailService;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${topic.failed-email}")
    private String failedEmailTopic;

    @Value("${topic.retry-email.topicName}")
    private String retryEmailTopic;

    @Value("${topic.retry-email.maxRetryCount}")
    private int maxRetryCount;

    @Value("${topic.retry-email.initialDelay}")
    private Long initialDelay;

    @Value("${topic.retry-email.backoffMultiplier}")
    private int backoffMultiplier;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${topic.email}", partitions = { "0", "1" } ))
    public void listenEmailTopic01(@Payload EmailRequest emailRequest,
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) int partitionId,
                                   @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            log.info("{} message received for partition-id {}, offset {} with message : {}", topic, partitionId, offset, emailRequest);
            emailService.sendMail(emailRequest);
        } catch(Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            if(isRetryableEmailException(e)){
                sendToRetryEmail(emailRequest);
            } else{
                sendToFailedEmail(emailRequest);
            }
        }
    }

    //@KafkaListener(topics = "${topic.email}")
    @KafkaListener(topicPartitions = @TopicPartition(topic = "${topic.email}", partitions = {"2"} ))
    public void listenEmailTopic2(@Payload EmailRequest emailRequest,
                                  @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                  @Header(KafkaHeaders.RECEIVED_PARTITION) int partitionId,
                                  @Header(KafkaHeaders.OFFSET) long offset) {
        try {
            log.info("{} message received for partition-id {}, offset {} with message : {}", topic, partitionId, offset, emailRequest);
            emailService.sendMail(emailRequest);
        } catch(Exception e){
            log.error("Exception occurred: {}", e.getMessage());
            if(isRetryableEmailException(e)) {
                sendToRetryEmail(emailRequest);
            }
            else {
                sendToFailedEmail(emailRequest);
            }
        }
    }

    @KafkaListener(topics = "${topic.retry-email.topicName}")
    public void listenRetryEmailTopic(@Payload EmailRequest emailRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        try {
            log.info("message received for retry-email topic... : {}", emailRequest);
            emailService.sendMail(emailRequest);
            log.info("message sent successfully for retry-email topic: {}", emailRequest);
        } catch(Exception e){
            log.error("Exception occurred: {}", e.getMessage());
            if (isRetryableEmailException(e)) {
                retryMessage(emailRequest, topic);
            } else {
                sendToFailedEmail(emailRequest);
            }
        }
    }

    private void sendToFailedEmail(EmailRequest emailRequest){
        kafkaTemplate.send(failedEmailTopic, emailRequest);
    }

    private void sendToRetryEmail(EmailRequest emailRequest){
        kafkaTemplate.send(retryEmailTopic, emailRequest);
    }

    private boolean isRetryableEmailException(Exception e) {
        if(e instanceof MailException){
            return true;
        } else if(e instanceof SocketTimeoutException){
            return true;
        } else if(e instanceof SocketException){
            return true;
        }
        return false;
    }

    private long getRetryDelay(int retryCount) {
        return initialDelay *  (long) Math.pow(backoffMultiplier, retryCount - 1);
    }

    private void retryMessage(EmailRequest emailRequest, String topic){
        int retryCount = emailRequest.getRetryCount();
        if (retryCount >= maxRetryCount) {
            sendToFailedEmail(emailRequest);
            return;
        }
        long delay = getRetryDelay(retryCount);
        emailRequest.setRetryCount(retryCount+1);
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, emailRequest);
        future.whenComplete((result, e) -> {
            if (e == null) {
                log.info("Sent message: {} with offset: {}", emailRequest, result.getRecordMetadata().offset());
            } else {
                log.error("Exception occurred: {}", e.getMessage());
                retryMessage(emailRequest, topic);
            }
        });
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}