package com.pxp.lmsleaveservice.helper;

import com.pxp.lmsleaveservice.constants.RequestError;
import com.pxp.lmsleaveservice.exceptions.InvalidRequestException;
import com.pxp.lmsleaveservice.repo.LeaveEntitlementRepo;
import com.pxp.lmsleaveservice.requests.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Service
public class LeaveServiceHelper {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    public BiConsumer<LeaveRequest, String> validateLeaveRequest = (leaveRequest, empId) -> {
        StringBuffer errorMessage = new StringBuffer();
        List leaveType = Arrays.asList("SL", "CL", "EL", "PAL", "LOP");
        try {
            if (empId == null || empId.isEmpty())
                errorMessage.append(RequestError.LeaveRequest.EMP_ID_EMPTY.msg());
            if (leaveRequest == null) {
                errorMessage.append(RequestError.LeaveRequest.LEAVE_REQ_EMPTY.msg());
            } else {
                if (null != leaveRequest.getLeaveType() && leaveRequest.getLeaveType().isEmpty())
                    errorMessage.append(RequestError.LeaveRequest.LEAVE_TYPE_EMPTY.msg());
                else if (!leaveType.contains(leaveRequest.getLeaveType()))
                    errorMessage.append(RequestError.LeaveRequest.INVALID_LEAVE_TYPE.msg());
                if (null != leaveRequest.getReason() && leaveRequest.getReason().isEmpty())
                    errorMessage.append(RequestError.LeaveRequest.LEAVE_REASON_EMPTY.msg());
                if(null == leaveRequest.getIsHalfDay() || leaveRequest.getIsHalfDay().toString().isEmpty())
                    errorMessage.append(RequestError.LeaveRequest.LEAVE_IS_HALF_DAY_EMPTY.msg());
                if (null != leaveRequest.getIsHalfDay() && leaveRequest.getIsHalfDay()) {
                    if (null != leaveRequest.getFirstOrSecondHalf()) {
                        if (!(leaveRequest.getFirstOrSecondHalf() == 1 || leaveRequest.getFirstOrSecondHalf() == 2))
                            errorMessage.append(RequestError.LeaveRequest.INVALID_LEAVE_FIRST_N_SECOND.msg());
                    } else {
                        errorMessage.append(RequestError.LeaveRequest.LEAVE_FIRST_N_SECOND_EMPTY.msg());
                    }
                }
                if (leaveRequest.getFrom() != null && leaveRequest.getTo() != null) {
                    if (leaveRequest.getFrom().compareTo(leaveRequest.getTo()) > 0)
                        errorMessage.append(RequestError.LeaveRequest.INVALID_DATE_TO_FROM_COMBINATION.msg());
                } else {
                    errorMessage.append(RequestError.LeaveRequest.DATE_TO_FROM_EMPTY.msg());
                }
            }
        } catch (Exception e) {
            throw new InvalidRequestException(RequestError.LeaveRequest.INVALID_REQ.msg());
        } finally {
            if (!errorMessage.isEmpty())
                throw new InvalidRequestException(errorMessage.toString());
        }
    };

    public Consumer<String> insufficientLeave = (leaveType) -> {
        throw new InvalidRequestException(RequestError.LeaveRequest.INSUFFICIENT_LEAVE.msg().replace("%leaveType%", leaveType));
    };

}