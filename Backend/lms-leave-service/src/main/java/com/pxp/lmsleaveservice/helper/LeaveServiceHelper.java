package com.pxp.lmsleaveservice.helper;

import com.pxp.lmsleaveservice.exceptions.InvalidRequestException;
import com.pxp.lmsleaveservice.repo.LeaveEntitlementRepo;
import com.pxp.lmsleaveservice.requests.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.ToIntBiFunction;
import java.util.function.ToLongBiFunction;

@Service
public class LeaveServiceHelper {

    @Autowired
    private LeaveEntitlementRepo leaveEntitlementRepo;

    public BiConsumer<LeaveRequest, String> validateLeaveRequest = (leaveRequest, empId) -> {
        StringBuffer errorMessage = new StringBuffer();
        List leaveType = Arrays.asList("SL", "CL", "EL", "PAL", "LOP");
        try {
            if (empId == null || empId.isEmpty())
                errorMessage.append("Employee Id cannot be empty;");
            if (leaveRequest == null) {
                errorMessage.append("Leave request cannot be empty;");
            } else {
                if (null != leaveRequest.getLeaveType() && leaveRequest.getLeaveType().isEmpty())
                    errorMessage.append("Leave type cannot be empty;");
                else if (!leaveType.contains(leaveRequest.getLeaveType()))
                    errorMessage.append("Invalid leave type;");
                if (null != leaveRequest.getReason() && leaveRequest.getReason().isEmpty())
                    errorMessage.append("Leave reason cannot be empty;");
                if (null != leaveRequest.getIsHalfDay() && leaveRequest.getIsHalfDay()) {
                    if (null != leaveRequest.getFirstOrSecondHalf()) {
                        if (!(leaveRequest.getFirstOrSecondHalf() == 1 || leaveRequest.getFirstOrSecondHalf() == 2))
                            errorMessage.append("Leave first/second half cannot be other than 1 or 2;");
                    } else {
                        errorMessage.append("Leave First/Second Half cannot be empty;");
                    }
                }
                if (leaveRequest.getFrom() != null && leaveRequest.getTo() != null) {
                    if (leaveRequest.getFrom().compareTo(leaveRequest.getTo()) > 0)
                        errorMessage.append("'Date to' should be greater than 'date from';");
                } else {
                    errorMessage.append("Leave from & to cannot be empty;");
                }
            }
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid Request");
        } finally {
            if (!errorMessage.isEmpty())
                throw new InvalidRequestException(errorMessage.toString());
        }
    };

    public Consumer<String> insufficientLeave = (leaveType) -> {
        throw new InvalidRequestException("Insufficient amount of "+leaveType+" available");
    };

}