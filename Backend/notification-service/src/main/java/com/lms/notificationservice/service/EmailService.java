package com.lms.notificationservice.service;

import com.lms.notificationservice.requests.EmailRequest;
import com.lms.notificationservice.service.interfaces.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(EmailRequest emailRequest) {
        log.info("sending mail to {} ....", emailRequest.getTo());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailRequest.getFrom());
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getBody());
        emailSender.send(message);
        log.info("mail sent to {}", emailRequest.getTo());
    }

}
