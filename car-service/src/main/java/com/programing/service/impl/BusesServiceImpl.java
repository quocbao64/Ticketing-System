package com.programing.service.impl;

import com.programing.model.UpdateQuantityTicket;
import com.programing.model.request.BusesRequest;
import com.programing.model.response.BusesResponse;
import com.programing.model.response.CarResponse;
import com.programing.entity.Buses;
import com.programing.entity.Car;
import com.programing.exception.BadRequestException;
import com.programing.exception.NotFoundException;
import com.programing.repository.BusesRepository;
import com.programing.repository.CarRepository;
import com.programing.service.BusesService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusesServiceImpl implements BusesService {

    private final CarRepository carRepository;
    private final BusesRepository busesRepository;

    @Override
    public List<BusesResponse> getAll() {

        List<Buses> busesList = busesRepository.findAll();

        List<BusesResponse> busesResponseList = busesList.stream().map(this::toBusesResponse).toList();

        return busesResponseList;
    }

    private BusesResponse toBusesResponse(Buses buses) {
        return BusesResponse.builder()
                .id(buses.getId())
                .car(toCarResponse(buses.getCar()))
                .route(buses.getRoute())
                .departureTime(buses.getDepartureTime())
                .destinationTime(buses.getDestinationTime())
                .driver(buses.getDriver())
                .ticketPrice(buses.getTicketPrice())
                .numberOfSeats(buses.getNumberOfSeats())
                .build();
    }

    private CarResponse toCarResponse(Car car) {
        return CarResponse.builder()
                .id(car.getId())
                .licensePlates(car.getLicensePlates())
                .yearOfManufacture(car.getYearOfManufacture())
                .numberOfSeats(car.getNumberOfSeats())
                .status(car.getStatus())
                .build();
    }

    @Override
    public BusesResponse getDetail(Long id) {
        Buses buses = busesRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        return toBusesResponse(buses);
    }

    @Override
    public void create(BusesRequest request) {
        if(request.getCarId()==null || request.getRoute()==null || request.getDepartureTime()==null ||
            request.getDestinationTime()==null || request.getDriver()==null || request.getTicketPrice()==null) {
            throw new BadRequestException(400, "Input full info, please.");
        }

        Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        Buses buses = Buses.builder()
                .car(car)
                .route(request.getRoute())
                .departureTime(request.getDepartureTime())
                .destinationTime(request.getDestinationTime())
                .driver(request.getDriver())
                .ticketPrice(request.getTicketPrice())
                .numberOfSeats(car.getNumberOfSeats())
                .build();

        busesRepository.save(buses);
    }

    @Override
    public void update(Long id, BusesRequest request) {
        Buses buses = busesRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        if(null!=request.getCarId()) {
            Car car = carRepository.findById(request.getCarId()).orElseThrow(() -> {
                throw new NotFoundException(404, "ID is not found!");
            });

            buses.setCar(car);
        }
        if(null!=request.getRoute())
            buses.setRoute(request.getRoute());
        if(null!=request.getDepartureTime())
            buses.setDepartureTime(request.getDepartureTime());
        if(null!=request.getDestinationTime())
            buses.setDestinationTime(request.getDestinationTime());
        if(null!=request.getDriver())
            buses.setDriver(request.getDriver());
        if(null!=request.getTicketPrice())
            buses.setTicketPrice(request.getTicketPrice());
        if(null!=request.getNumberOfSeats())
            buses.setNumberOfSeats(request.getNumberOfSeats());

        busesRepository.save(buses);
    }

    @Override
    public void delete(Long id) {
        Buses buses = busesRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        busesRepository.delete(buses);
    }

//    @Override
    @KafkaListener(
            topics = "cancel-order"
            ,groupId = "group_id_cancel_order"
    )
    public void increaseTicket(UpdateQuantityTicket request) {
        Buses buses = busesRepository.findById(request.getId()).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        if(null!=request.getQuantity()) buses.increase(request.getQuantity());

        busesRepository.save(buses);
    }

    @KafkaListener(
            topics = "create-order",
            groupId = "group_id_create_order"
    )
    public void reduceTicket(UpdateQuantityTicket request) {
        try {
            System.out.println("Request: " + request.getId());
            Buses buses = busesRepository.findById(request.getId())
                    .orElseThrow(() -> new NotFoundException(404, "ID is not found!"));

            if (request.getQuantity() != null) {
                buses.reduce(request.getQuantity());
                busesRepository.save(buses);
            }
        } catch (NotFoundException ex) {
            // Handle NotFoundException here
            // For example, you can log the error or send a response to a specific topic.
            System.err.println("NotFoundException: " + ex.getMessage());
        }
    }

}
