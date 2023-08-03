package com.programing.service;

import com.programing.model.request.CarRequest;
import com.programing.model.response.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<CarResponse> getAll();

    CarResponse getDetail(Long id);

    void create(CarRequest request);

    void update(Long id, CarRequest request);

}
