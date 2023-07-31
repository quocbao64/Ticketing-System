package com.programing.dto.request;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CarRequest {

    private String licensePlates;
    private String yearOfManufacture;
    private Integer numberOfSeats;
    private Boolean status;

}
