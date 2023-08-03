package com.programing.model.request;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CarRequest {

    private String licensePlates;
    private String yearOfManufacture;
    private Integer numberOfSeats;
    private Boolean status;

}
