package com.lms.attendanceservice.responses;

import java.sql.Time;
import java.util.Date;

public record SwipeResponse(String empId, Date date, Time time){

}
