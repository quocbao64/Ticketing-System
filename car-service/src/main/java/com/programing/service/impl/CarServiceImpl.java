package com.programing.service.impl;

import com.programing.model.request.CarRequest;
import com.programing.model.response.CarResponse;
import com.programing.entity.Car;
import com.programing.exception.BadRequestException;
import com.programing.exception.NotFoundException;
import com.programing.repository.CarRepository;
import com.programing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<CarResponse> getAll() {

        List<Car> cars = carRepository.findAll();

        List<CarResponse> carResponseList = cars.stream().map(car ->
                CarResponse.builder()
                        .id(car.getId())
                        .licensePlates(car.getLicensePlates())
                        .yearOfManufacture(car.getYearOfManufacture())
                        .numberOfSeats(car.getNumberOfSeats())
                        .status(car.getStatus())
                        .build()
        ).toList();

        return carResponseList;
    }

    @Override
    public CarResponse getDetail(Long id) {

        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        return CarResponse.builder()
                .id(car.getId())
                .licensePlates(car.getLicensePlates())
                .yearOfManufacture(car.getYearOfManufacture())
                .numberOfSeats(car.getNumberOfSeats())
                .status(car.getStatus())
                .build();

    }

    @Override
    public void create(CarRequest request) {
        if(request.getLicensePlates()==null || request.getYearOfManufacture()==null ||
            request.getNumberOfSeats()==null || request.getStatus()==null) {
            throw new BadRequestException(400, "Input full info, please!");
        }

        Car car = Car.builder()
                .licensePlates(request.getLicensePlates())
                .yearOfManufacture(request.getYearOfManufacture())
                .numberOfSeats(request.getNumberOfSeats())
                .status(request.getStatus())
                .build();

        carRepository.save(car);
    }

    @Override
    public void update(Long id, CarRequest request) {
        Car car = carRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        if(request.getLicensePlates()!=null) {
            car.setLicensePlates(request.getLicensePlates());
        }
        if(request.getYearOfManufacture()!=null) {
            car.setYearOfManufacture(request.getYearOfManufacture());
        }
        if(request.getNumberOfSeats()!=null) {
            car.setNumberOfSeats(request.getNumberOfSeats());
        }
        if(request.getStatus()!=null) {
            car.setStatus(request.getStatus());
        }

        carRepository.save(car);
    }

}
