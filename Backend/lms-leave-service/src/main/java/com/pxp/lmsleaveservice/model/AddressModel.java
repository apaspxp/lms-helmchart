package com.pxp.lmsleaveservice.model;

import jakarta.persistence.Column;

public record AddressModel(int addressId,
                           String addressLine1,
                           String addressLine2,
                           String locality,
                           int pinCode,
                           String city,
                           String state,
                           String country,
                           String region) {
}
