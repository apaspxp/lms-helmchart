package com.lms.notificationservice.requests;

import lombok.Data;

@Data
public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String attachment;
}
