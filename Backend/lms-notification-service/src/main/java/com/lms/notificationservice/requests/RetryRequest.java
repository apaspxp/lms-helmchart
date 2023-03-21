package com.lms.notificationservice.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetryRequest {
    private int attempts;
    private Object data;
}
