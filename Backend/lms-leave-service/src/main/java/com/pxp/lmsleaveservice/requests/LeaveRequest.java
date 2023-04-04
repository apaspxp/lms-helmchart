package com.pxp.lmsleaveservice.requests;

import lombok.Data;

import java.util.Date;

@Data
public class LeaveRequest {
    private String leaveType;
    private Date from;
    private Date To;
    private Boolean isHalfDay;
    private Integer firstOrSecondHalf;
    private String reason;
}
