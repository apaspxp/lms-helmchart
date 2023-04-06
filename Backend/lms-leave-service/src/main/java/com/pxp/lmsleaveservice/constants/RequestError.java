package com.pxp.lmsleaveservice.constants;

import lombok.ToString;

public class RequestError {

    @ToString
    public static enum LeaveRequest {
        //Leave Request Error Messages : Empty
        EMP_ID_EMPTY("Employee Id cannot be empty;"),
        LEAVE_REQ_EMPTY("Leave request cannot be empty;"),
        LEAVE_TYPE_EMPTY("Leave type cannot be empty;"),
        LEAVE_REASON_EMPTY("Leave reason cannot be empty;"),
        LEAVE_FIRST_N_SECOND_EMPTY("Leave First/Second Half cannot be empty;"),
        LEAVE_IS_HALF_DAY_EMPTY("Leave 'is half day?' cannot be empty;"),
        DATE_TO_FROM_EMPTY("Leave from & to cannot be empty;"),

        //Leave Request Error Messages : Invalid
        INVALID_LEAVE_TYPE("Invalid leave type;"),
        INVALID_LEAVE_FIRST_N_SECOND("Leave first/second half cannot be other than 1 or 2;"),
        INVALID_DATE_TO_FROM_COMBINATION("'Date to' should be greater than 'date from';"),
        INVALID_REQ("Invalid Request"),
        INSUFFICIENT_LEAVE("Insufficient amount of %leaveType% available");

        private String error;
        private LeaveRequest(String error) {this.error = error;}
        public String msg() {return error;}
    }

}