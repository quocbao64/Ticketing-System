package com.programing.service.impl;

import com.programing.entity.Customer;
import com.programing.exception.NotFoundException;
import com.programing.model.request.CustomerRequest;
import com.programing.model.response.CustomerResponse;
import com.programing.repository.CustomerRepository;
import com.programing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Map<String, Object> getAll(Pageable pageable) {

        Page<Customer> customerPage = customerRepository.findAll(pageable);

        List<CustomerResponse> customerResponses = customerPage.getContent().stream().map(this::toCustomerResponse).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", customerResponses);
        response.put("currentPage", customerPage.getNumber());
        response.put("totalItems", customerPage.getTotalElements());
        response.put("totalPages", customerPage.getTotalPages());

        return response;
    }

    private CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .numberPhone(customer.getNumberPhone())
                .email(customer.getEmail())
                .fullName(customer.getFullName())
                .build();
    }

    @Override
    public CustomerResponse getDetail(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        return toCustomerResponse(customer);
    }

    @Override
    public void update(Long id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundException(404, "ID is not found!");
        });

        if(null!=request.getPhoneNumber())
            customer.setNumberPhone(request.getPhoneNumber());
        if(null!=request.getEmail())
            customer.setEmail(request.getEmail());
        if(null!= request.getFullName())
            customer.setFullName(request.getFullName());

        customerRepository.save(customer);
    }
}
