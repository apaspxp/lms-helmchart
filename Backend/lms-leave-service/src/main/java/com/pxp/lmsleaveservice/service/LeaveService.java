package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.exceptions.InvalidRequestException;
import com.pxp.lmsleaveservice.helper.LeaveServiceHelper;
import com.pxp.lmsleaveservice.repo.LeaveEntitlementRepo;
import com.pxp.lmsleaveservice.requests.LeaveRequest;
import com.pxp.lmsleaveservice.service.interfaces.ILeaveService;
import com.pxp.lmsleaveservice.utility.CommonUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

@Service
@Slf4j
public class LeaveService implements ILeaveService {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    @Autowired
    private LeaveServiceHelper leaveServiceHelper;

    @Autowired
    private CommonUtility commonUtility;

    @Override
    public LeaveEntitlementEntity fetchLeaveBalance(String employeeId) {
        LeaveEntitlementEntity entity = leaveEntitlementRepo.findById(employeeId).orElse(null);
        if(entity == null)
            throw new ResourceNotFoundException("Invalid Employee ID");
        return entity;
    }

    @Override
    @Transactional(transactionManager = "leaveServiceTransactionManager")
    public LeaveEntitlementEntity applyLeave(String employeeId, LeaveRequest applyLeaveRequest){
        leaveServiceHelper.validateLeaveRequest.accept(applyLeaveRequest, employeeId);
        LeaveEntitlementEntity entity = leaveEntitlementRepo.findById(employeeId).orElse(null);
        if(entity == null)
            throw new ResourceNotFoundException("Invalid Employee ID");

        /*
        TODO : Additional validations required to check whether leave applied already -> Linked with LeaveHistory Table
        TODO : Store Leave History to LeaveHistory Table
        */

        //In this point request is validated, now check sufficient balance available or not
        int daysBetween = commonUtility.getDifferenceDays.applyAsInt(applyLeaveRequest.getFrom(), applyLeaveRequest.getTo());

        switch (applyLeaveRequest.getLeaveType()){

            //Sick Leave
            case "SL":
                if(entity.getSlBal() >= daysBetween){
                    entity.setSlBal(entity.getSlBal()-daysBetween);
                } else {
                    leaveServiceHelper.insufficientLeave.accept(applyLeaveRequest.getLeaveType());
                }
                break;

            //Casual Leave
            case "CL":
                if(entity.getClBal() >= daysBetween){
                    entity.setClBal(entity.getClBal()-daysBetween);
                } else {
                    leaveServiceHelper.insufficientLeave.accept(applyLeaveRequest.getLeaveType());
                }
                break;

            //Earned Leave
            case "EL":
                if(entity.getElBal() >= daysBetween){
                    entity.setElBal(entity.getElBal()-daysBetween);
                } else {
                    leaveServiceHelper.insufficientLeave.accept(applyLeaveRequest.getLeaveType());
                }
                break;

            //Paternity Leave
            case "PAL":
                if(entity.getPalBal() >= daysBetween){
                    entity.setPalBal(entity.getPalBal()-daysBetween);
                } else {
                    leaveServiceHelper.insufficientLeave.accept(applyLeaveRequest.getLeaveType());
                }
                break;

            //Loss Of Pay
            case "LOP":
                entity.setLopOverdraft(entity.getLopOverdraft()+daysBetween);
                break;

        }
        leaveEntitlementRepo.save(entity);

        return entity;
    }

}

