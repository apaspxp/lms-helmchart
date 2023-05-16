package com.lms.attendanceservice.service;

import com.lms.attendanceservice.constants.AttendanceConstants;
import com.lms.attendanceservice.constants.ResponseStatus;
import com.lms.attendanceservice.entity.AttendanceEntity;
import com.lms.attendanceservice.exceptions.AttendanceServiceException;
import com.lms.attendanceservice.helper.SwipeServiceHelper;
import com.lms.attendanceservice.responses.Response;
import com.lms.attendanceservice.responses.SwipeResponse;
import com.lms.attendanceservice.repo.AttendanceRepo;
import com.lms.attendanceservice.service.interfaces.ISwipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SwipeService implements ISwipeService {

    @Autowired
    private AttendanceRepo attendanceRepo;

    @Autowired
    private SwipeServiceHelper swipeServiceHelper;

    @Override
    public Response<SwipeResponse> swipe(String swipeOption) {
        log.info("Entered into method swipe()");
        swipeServiceHelper.validateLeaveRequest.accept(swipeOption);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String empId = "18"; // TODO : Later fetch from principal
        Date today = new Date();
        Time time = new Time(today.getTime());
        Response response;
        try {
            today = dateFormat.parse(dateFormat.format(today));
            AttendanceEntity data = attendanceRepo.findByEmployeeIdAndDate(empId, today);

            if(swipeOption.equalsIgnoreCase(AttendanceConstants.SwipeOption.IN.value())){
                log.info("Swipe in called");
                if(data!=null){
                    log.info("Swipe in success for empId {}", empId);
                    response = new Response<>(new SwipeResponse(empId, today, time), ResponseStatus.SUCCESS, "Successfully swiped in");
                } else{
                    AttendanceEntity attendanceEntity = new AttendanceEntity();
                    attendanceEntity.setAttendanceRecordType(AttendanceConstants.AttendanceRecordType.WFH.value());
                    attendanceEntity.setEmployeeId(empId);
                    attendanceEntity.setDate(today);
                    attendanceEntity.setInTime(time);
                    attendanceRepo.save(attendanceEntity);
                    log.info("Day's first swipe in success for empId {}", empId);
                    response = new Response<>(new SwipeResponse(empId, today, time), ResponseStatus.SUCCESS, "Successfully swiped in");
                }
            } else{
                if(data!=null){
                    Date inTime = timeFormat.parse(String.valueOf(data.getInTime()));
                    Date outTime = timeFormat.parse(String.valueOf(time));
                    long duration = outTime.getTime()-inTime.getTime();
                    data.setDuration(TimeUnit.MILLISECONDS.toMinutes(duration));
                    data.setOutTime(time);
                    attendanceRepo.save(data);
                    log.info("Swipe out success for empId {}", empId);
                    response = new Response<>(new SwipeResponse(empId, today, time), ResponseStatus.SUCCESS, "Successfully swiped out");
                } else{
                    log.info("Swipe out failed for empId {}", empId);
                    response = new Response<>(new SwipeResponse(empId, today, time), ResponseStatus.FAILED, "Failed! swipe in first");
                }
            }
            return response;
        } catch (Exception e){
            e.printStackTrace();
            throw new AttendanceServiceException("Internal Server Error");
        }
    }
}

