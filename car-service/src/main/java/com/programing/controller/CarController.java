package com.programing.controller;

import com.programing.dto.request.CarRequest;
import com.programing.dto.response.CarResponse;
import com.programing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public List<CarResponse> getAll() {
        return carService.getAll();
    }

    @GetMapping("/{id}")
    public CarResponse getDetail(@PathVariable("id")Long id) {
        return carService.getDetail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody CarRequest request) {

        carService.create(request);

        return "Create car is successfully";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id")Long id,
                         @RequestBody CarRequest request) {

        carService.update(id, request);

        return "Update car is successfully";
    }

}
