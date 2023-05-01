package com.lms.attendanceservice.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AttendanceId implements Serializable {
    private String employeeId;
    private Date date;
}
