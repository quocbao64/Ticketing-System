package com.programing.controller;

import com.programing.model.request.BusesRequest;
import com.programing.model.response.BusesResponse;
import com.programing.service.BusesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@RequiredArgsConstructor
public class BusesController {

    private final BusesService busesService;

    @GetMapping
    public List<BusesResponse> getAll() {
        return busesService.getAll();
    }

    @GetMapping("/{id}")
    public BusesResponse getDetail(@PathVariable("id")Long id) {
        return busesService.getDetail(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody BusesRequest request) {
        busesService.create(request);

        return "Create buses is successfully";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id")Long id,
                         @RequestBody BusesRequest request) {
        busesService.update(id, request);

        return "Update buses is successfully";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id) {
        busesService.delete(id);
    }

//    @PostMapping("/{id}/increase-ticket")
//    public void increaseTicket(@PathVariable("id")Long id,
//                               @RequestParam("quantity")Integer quantity) {
//        busesService.increaseTicket(id, quantity);
//    }
//
//    @PostMapping("/{id}/reduce-ticket")
//    public void reduceTicket(@PathVariable("id")Long id,
//                               @RequestParam("quantity")Integer quantity) {
//        busesService.reduceTicket(id, quantity);
//    }

}
