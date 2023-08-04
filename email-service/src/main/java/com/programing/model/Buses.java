package com.programing.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor
public class Buses {
    private Long id;
    private Car car;
    private String route;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
    private String driver;
    private Double ticketPrice;
    private Integer numberOfSeats;
}
