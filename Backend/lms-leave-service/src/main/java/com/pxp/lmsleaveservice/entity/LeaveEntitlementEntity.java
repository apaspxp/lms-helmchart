package com.pxp.lmsleaveservice.entity;

import jakarta.persistence.*;
import lombok.*;

/*
 *   SL - Sick Leave
 *   CL - Casual Leave
 *   EL - Earned Leave
 *   PAL - Paternity Leave
 *   LOP - Loss of Pay
 * */

@Data
@Entity
@Table(name="LEAVE_ENTITLEMENT")
public class LeaveEntitlementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EMPLOYEE_ID")
    private String employeeId;

    //SL
    @Column(name="SICK_LEAVE_BALANCE")
    private Integer slBal;
    @Column(name="SICK_LEAVE_ENTITLE")
    private Integer slEnt;

    //CL
    @Column(name="CASUAL_LEAVE_BALANCE")
    private Integer clBal;
    @Column(name="CASUAL_LEAVE_ENTITLE")
    private Integer clEnt;

    //EL
    @Column(name="EARNED_LEAVE_BALANCE")
    private Integer elBal;
    @Column(name="EARNED_LEAVE_ENTITLE")
    private Integer elEnt;

    //PAL
    @Column(name="PATERNITY_LEAVE_BALANCE")
    private Integer palBal;
    @Column(name="PATERNITY_LEAVE_ENTITLE")
    private Integer palEnt;

    @Column(name="LOP_OVERDRAFT")
    private Integer lopOverdraft;

}
