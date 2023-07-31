package com.programing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_car")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "license_plates")
    private String licensePlates;

    @Column(name = "year_of_manufacture")
    private String yearOfManufacture;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @Column(name = "status")
    private Boolean status;

    // Constructors, getters, setters, etc.
}

