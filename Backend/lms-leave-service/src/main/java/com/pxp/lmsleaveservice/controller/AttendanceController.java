package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.service.interfaces.IAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

    @RequestMapping(value = "/fetchLeaveBalance/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<LeaveEntitlementEntity> fetchLeaveBalance(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(attendanceService.fetchLeaveBalance(employeeId), HttpStatus.OK);
    }

    @RequestMapping(value = "/applyLeave/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<LeaveEntitlementEntity> applyLeave(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(attendanceService.applyLeave(employeeId), HttpStatus.OK);
    }

}
