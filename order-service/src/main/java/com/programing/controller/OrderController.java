package com.programing.controller;

import com.programing.model.request.OrderRequest;
import com.programing.model.response.OrderResponse;
import com.programing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(name = "statusPayment", required = false)Boolean statusPayment) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(orderService.getAll(statusPayment, pageable));
    }

    @GetMapping("/{id}")
    public OrderResponse getDetail(@PathVariable("id")Long id) {
        return orderService.getDetail(id);
    }

    @PostMapping
    public String create(@RequestBody OrderRequest request) {
        orderService.create(request);

        return "Create order is successfully.";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id")Long id,
                         @RequestBody OrderRequest request) {
        orderService.update(id, request);

        return "Update order is successfully.";
    }

    @PutMapping("/{id}")
    public String updateStatusPay(@PathVariable("id")Long id) {
        orderService.updateStatusPay(id);

        return "Update status payment order is successfully.";
    }

    @DeleteMapping("/{id}")
    public String cancelOrder(@PathVariable("id")Long id) {
        orderService.cancel(id);

        return "Cancel order is successfully.";
    }
}
