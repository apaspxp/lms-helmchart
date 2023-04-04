package com.pxp.lmsleaveservice.constants;

public class RequestError {

    //Leave Request Error Messages : Empty
    public static final String EMP_ID_EMPTY = "Employee Id cannot be empty;";
    public static final String LEAVE_REQ_EMPTY = "Leave request cannot be empty;";
    public static final String LEAVE_TYPE_EMPTY = "Leave type cannot be empty;";
    public static final String LEAVE_REASON_EMPTY = "Leave reason cannot be empty;";
    public static final String LEAVE_FIRST_N_SECOND_EMPTY = "Leave First/Second Half cannot be empty;";
    public static final String DATE_TO_FROM_EMPTY = "Leave from & to cannot be empty;";
    //Leave Request Error Messages : Invalid
    public static final String INVALID_LEAVE_TYPE = "Invalid leave type;";
    public static final String INVALID_LEAVE_FIRST_N_SECOND = "Leave first/second half cannot be other than 1 or 2;";
    public static final String INVALID_DATE_TO_FROM_COMBINATION = "'Date to' should be greater than 'date from';";
    public static final String INVALID_REQ = "Invalid Request";
    public static final String INSUFFICIENT_LEAVE = "Insufficient amount of %leaveType% available";

    //Other

}
