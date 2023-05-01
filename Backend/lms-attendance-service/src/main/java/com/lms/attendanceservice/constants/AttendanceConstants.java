package com.lms.attendanceservice.constants;

import lombok.ToString;

public class AttendanceConstants {

    @ToString
    public static enum AttendanceRecordType {
        WFH("WFH"),
        WFO("WFO"),
        ONSITE("ONSITE");

        private String value;
        private AttendanceRecordType(String value) {this.value = value;}
        public String value() {return value;}
    }

    @ToString
    public static enum SwipeOption {
        IN("IN"),
        OUT("OUT");

        private String value;
        private SwipeOption(String value) {this.value = value;}
        public String value() {return value;}
    }


    @ToString
    public static enum AttendanceRequest {
        INVALID_LEAVE_OPTION("Invalid leave option");

        private String error;
        private AttendanceRequest(String error) {this.error = error;}
        public String msg() {return error;}
    }

}
