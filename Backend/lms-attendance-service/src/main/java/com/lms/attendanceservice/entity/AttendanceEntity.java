package com.lms.attendanceservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ATTENDANCE")
@IdClass(AttendanceId.class)
public class AttendanceEntity {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;

    @Id
    @Column(name = "DATE")
    private Date date;

    @Column(name = "IN_TIME")
    private Time inTime;

    @Column(name = "OUT_TIME")
    private Time outTime;

    @Column(name = "DURATION")
    private Long duration;

    @Column(name = "ATTENDANCE_RECORD_TYPE")
    private String attendanceRecordType;

    @Column(name = "APPROVAL_STATUS")
    private String approvalStatus;

    @Column(name = "APPROVED_BY")
    private String approvedBy;

    @Column(name = "COMMENTS")
    private String comments;
}
