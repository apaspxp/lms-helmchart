package com.lms.notificationservice.service.interfaces;

import com.lms.notificationservice.requests.EmailRequest;

public interface IEmailService {
    public void sendMail(EmailRequest emailRequest);
}
