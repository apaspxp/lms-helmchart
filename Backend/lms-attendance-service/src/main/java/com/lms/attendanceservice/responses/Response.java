package com.lms.attendanceservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private String status;
    private String message;
    private LocalDateTime timestamp;
    private T payload;

    public Response(String status, String message) {
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public Response(T payload, String status, String message) {
        this.message = message;
        this.status = status;
        this.payload = payload;
        this.timestamp = LocalDateTime.now();
    }
}
