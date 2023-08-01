package com.programing.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class OrderRequest {

    private String phoneNumber;
    private String email;
    private String fullName;
    private Long busesId;
    private String pickUpLocation;
    private Integer quantity;

}
