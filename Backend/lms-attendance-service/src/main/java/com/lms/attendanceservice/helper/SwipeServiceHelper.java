package com.lms.attendanceservice.helper;

import com.lms.attendanceservice.constants.AttendanceConstants;
import com.lms.attendanceservice.exceptions.InvalidRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class SwipeServiceHelper {

    public Consumer<String> validateLeaveRequest = (swipeOption) -> {
        log.info("Validating swipe request");
        if(!(swipeOption.equalsIgnoreCase("in") || swipeOption.equalsIgnoreCase("out"))) {
            log.info("Swipe option is invalid");
            throw new InvalidRequestException(AttendanceConstants.AttendanceRequest.INVALID_LEAVE_OPTION.msg());
        }
        log.info("Swipe request validated");
    };

}