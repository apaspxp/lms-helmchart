package com.pxp.lmsleaveservice.service.interfaces;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;

public interface IAttendanceService {

    public LeaveEntitlementEntity fetchLeaveBalance (String employeeId);

    public LeaveEntitlementEntity applyLeave (String employeeId);

}
