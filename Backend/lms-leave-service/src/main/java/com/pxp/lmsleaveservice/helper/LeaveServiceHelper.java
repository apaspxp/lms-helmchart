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
                errorMessage.append(RequestError.EMP_ID_EMPTY);
            if (leaveRequest == null) {
                errorMessage.append(RequestError.LEAVE_REQ_EMPTY);
            } else {
                if (null != leaveRequest.getLeaveType() && leaveRequest.getLeaveType().isEmpty())
                    errorMessage.append(RequestError.LEAVE_TYPE_EMPTY);
                else if (!leaveType.contains(leaveRequest.getLeaveType()))
                    errorMessage.append(RequestError.INVALID_LEAVE_TYPE);
                if (null != leaveRequest.getReason() && leaveRequest.getReason().isEmpty())
                    errorMessage.append(RequestError.LEAVE_REASON_EMPTY);
                if (null != leaveRequest.getIsHalfDay() && leaveRequest.getIsHalfDay()) {
                    if (null != leaveRequest.getFirstOrSecondHalf()) {
                        if (!(leaveRequest.getFirstOrSecondHalf() == 1 || leaveRequest.getFirstOrSecondHalf() == 2))
                            errorMessage.append(RequestError.INVALID_LEAVE_FIRST_N_SECOND);
                    } else {
                        errorMessage.append(RequestError.LEAVE_FIRST_N_SECOND_EMPTY);
                    }
                }
                if (leaveRequest.getFrom() != null && leaveRequest.getTo() != null) {
                    if (leaveRequest.getFrom().compareTo(leaveRequest.getTo()) > 0)
                        errorMessage.append(RequestError.INVALID_DATE_TO_FROM_COMBINATION);
                } else {
                    errorMessage.append(RequestError.DATE_TO_FROM_EMPTY);
                }
            }
        } catch (Exception e) {
            throw new InvalidRequestException(RequestError.INVALID_REQ);
        } finally {
            if (!errorMessage.isEmpty())
                throw new InvalidRequestException(errorMessage.toString());
        }
    };

    public Consumer<String> insufficientLeave = (leaveType) -> {
        throw new InvalidRequestException(RequestError.INSUFFICIENT_LEAVE.replace("%leaveType%", leaveType));
    };

}