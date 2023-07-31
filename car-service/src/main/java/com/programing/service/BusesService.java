package com.programing.service;

import com.programing.dto.request.BusesRequest;
import com.programing.dto.response.BusesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusesService {

    List<BusesResponse> getAll();

    BusesResponse getDetail(Long id);

    void create(BusesRequest request);

    void update(Long id, BusesRequest request);

    void delete(Long id);

    void increaseTicket(Long id, Integer quantity);

    void reduceTicket(Long id, Integer quantity);

}
