package com.programing.dto.response;

import com.programing.entity.Car;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BusesResponse {

    private Long id;
    private CarResponse car;
    private String route;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
    private String driver;
    private Double ticketPrice;
    private Integer numberOfSeats;

}
