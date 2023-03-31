package com.lms.attendanceservice.controller;

import com.lms.attendanceservice.entity.LeaveEntitlementEntity;
import com.lms.attendanceservice.repo.LeaveEntitlementRepo;
import com.lms.attendanceservice.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    @GetMapping("/getLeaveEntitlement")
    public List<LeaveEntitlementEntity> getLeaveEntitlement(){
        return leaveEntitlementRepo.findAll();
    }

}
