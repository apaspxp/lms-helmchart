package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.repo.LeaveEntitlementRepo;
import com.pxp.lmsleaveservice.service.interfaces.IAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttendanceService implements IAttendanceService {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    @Override
    public LeaveEntitlementEntity fetchLeaveBalance(String employeeId) {
        return leaveEntitlementRepo.findById(employeeId).orElse(null);
    }

    @Override
    public LeaveEntitlementEntity applyLeave(String employeeId) {
        return leaveEntitlementRepo.findById(employeeId).orElse(null);
    }
}

