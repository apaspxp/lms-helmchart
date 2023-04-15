package com.pxp.lmsleaveservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="LocationId")
    private int locationId;
    @Column(name="Region")
    private String region;
    @Column(name="Country")
    private String country;
    @Column(name="City")
    private String city;
    @Column(name="Building")
    private String building;
    @Column(name="AddressLine1")
    private String addressLine1;
    @Column(name="AddressLine2")
    private String addressLine2;
    @Column(name="PinCode")
    private int pinCode;
    @Column(name="State")
    private String state;

    @OneToMany(targetEntity = EmployeeEntity.class, cascade = CascadeType.ALL )
    @JoinColumn(name = "LocationId", referencedColumnName = "LocationId", insertable=false, updatable=false)
    private List<EmployeeEntity> employee;
}
