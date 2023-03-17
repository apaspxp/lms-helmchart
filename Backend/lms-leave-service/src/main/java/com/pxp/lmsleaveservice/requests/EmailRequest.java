package com.pxp.lmsleaveservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String attachment;
}
