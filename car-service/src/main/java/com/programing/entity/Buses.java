package com.programing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_buses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Buses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private String route;
    private LocalDateTime departureTime;
    private LocalDateTime destinationTime;
    private String driver;
    private Double ticketPrice;
    private Integer numberOfSeats;

    public void increase(int quantity) {
        this.numberOfSeats += quantity;
    }

    public void reduce(int quantity) {
        this.numberOfSeats -= quantity;
    }

}
