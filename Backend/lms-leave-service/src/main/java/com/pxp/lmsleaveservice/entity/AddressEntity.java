package com.pxp.lmsleaveservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ADDRESS")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="address_id")
    private int addressId;
    @Column(name="AddressLine1")
    private String addressLine1;
    @Column(name="AddressLine2")
    private String addressLine2;
    @Column(name="Locality")
    private String locality;
    @Column(name="PinCode")
    private int pinCode;
    @Column(name="City")
    private String city;
    @Column(name="State")
    private String state;

    @Column(name="Country")
    private String country;

    @Column(name="Region")
    private String region;

}
