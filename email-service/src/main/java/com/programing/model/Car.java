package com.programing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;
    private String licensePlates;
    private String yearOfManufacture;
    private Integer numberOfSeats;
    private Boolean status;
}
