package com.programing.service;

import com.programing.model.request.BusesRequest;
import com.programing.model.response.BusesResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BusesService {

    List<BusesResponse> getAll();

    BusesResponse getDetail(Long id);

    void create(BusesRequest request);

    void update(Long id, BusesRequest request);

    void delete(Long id);

//    void increaseTicket(UpdateQuantityTicket request);

//    void reduceTicket(UpdateQuantityTicket request);

}
