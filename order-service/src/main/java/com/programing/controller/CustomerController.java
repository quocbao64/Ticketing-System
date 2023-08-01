package com.programing.controller;

import com.programing.model.request.CustomerRequest;
import com.programing.model.response.CustomerResponse;
import com.programing.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(customerService.getAll(pageable));
    }

    @GetMapping("/{id}")
    public CustomerResponse getDetail(@PathVariable("id")Long id) {

        return customerService.getDetail(id);
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id")Long id,
                         @RequestBody CustomerRequest request) {

        customerService.update(id, request);

        return "Update customer is successfully.";
    }
}
