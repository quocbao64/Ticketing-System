package com.programing.service;

import com.programing.model.request.OrderRequest;
import com.programing.model.response.OrderResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface OrderService {

    Map<String, Object> getAll(Boolean statusPayment, Pageable pageable);

    OrderResponse getDetail(Long id);

    void create(OrderRequest request);

    void update(Long id, OrderRequest request);

    void updateStatusPay(Long id);

    void cancel(Long id);

}
