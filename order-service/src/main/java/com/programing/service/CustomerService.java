package com.programing.service;

import com.programing.model.request.CustomerRequest;
import com.programing.model.response.CustomerResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CustomerService {

    Map<String, Object> getAll(Pageable pageable);

    CustomerResponse getDetail(Long id);

    void update(Long id, CustomerRequest request);

}
