package com.programing.model.request;

import lombok.Data;

@Data
public class CustomerRequest {

    private String phoneNumber;
    private String email;
    private String fullName;

}
