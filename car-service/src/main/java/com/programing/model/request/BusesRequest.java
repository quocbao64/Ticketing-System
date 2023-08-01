package com.programing.model.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BusesRequest {

    private Long carId;
    private String route;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
    private String driver;
    private Double ticketPrice;
    private Integer numberOfSeats;

}
