package com.pxp.lmsleaveservice.service.interfaces;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.requests.LeaveRequest;

public interface ILeaveService {

    public LeaveEntitlementEntity fetchLeaveBalance (String employeeId);

    public LeaveEntitlementEntity applyLeave (String employeeId, LeaveRequest applyLeaveRequest);

}
