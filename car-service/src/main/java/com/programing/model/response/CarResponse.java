package com.programing.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResponse {

    private Long id;
    private String licensePlates;
    private String yearOfManufacture;
    private Integer numberOfSeats;
    private Boolean status;

}
