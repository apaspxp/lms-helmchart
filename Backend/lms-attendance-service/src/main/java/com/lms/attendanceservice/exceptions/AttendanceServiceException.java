package com.lms.attendanceservice.exceptions;

public class AttendanceServiceException extends RuntimeException {
    public AttendanceServiceException(String errorMessage) {
        super(errorMessage);
    }

    public AttendanceServiceException() {}
}