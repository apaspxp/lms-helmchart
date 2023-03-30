package com.lms.attendanceservice.service;

import com.lms.attendanceservice.repo.LeaveEntitlementRepo;
import com.lms.attendanceservice.service.interfaces.IAttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AttendanceService implements IAttendanceService {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    @Override
    public void test() {

    }
}

