package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.helper.LeaveServiceHelper;
import com.pxp.lmsleaveservice.repo.LeaveEntitlementRepo;
import com.pxp.lmsleaveservice.requests.LeaveRequest;
import com.pxp.lmsleaveservice.service.interfaces.ILeaveService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LeaveService implements ILeaveService {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    @Autowired
    private LeaveServiceHelper leaveServiceHelper;

    @Override
    public LeaveEntitlementEntity fetchLeaveBalance(String employeeId) {
        LeaveEntitlementEntity entity = leaveEntitlementRepo.findById(employeeId).orElse(null);
        if(entity == null)
            throw new ResourceNotFoundException("Invalid Employee ID");
        return entity;
    }

    @Override
    public LeaveEntitlementEntity applyLeave(String employeeId, LeaveRequest applyLeaveRequest){
        LeaveEntitlementEntity entity = leaveEntitlementRepo.findById(employeeId).orElse(null);
        leaveServiceHelper.validateLeaveRequest.accept(applyLeaveRequest);
        if(entity == null)
            throw new ResourceNotFoundException("Invalid Employee ID");
        return entity;
    }

}

