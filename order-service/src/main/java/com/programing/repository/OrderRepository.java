package com.programing.repository;

import com.programing.entity.Customer;
import com.programing.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAllByStatusPayment(Boolean status, Pageable pageable);
}
