package com.pxp.lmsleaveservice.entity;


import jakarta.persistence.*;
import lombok.Data;

@Table(name="employee")
@Entity
@Data
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private int employeeId;

    @Column(name="name")
    private String name;

}
