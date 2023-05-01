package com.lms.attendanceservice.exceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }
}