package com.programing.model.response;

import com.programing.model.Buses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class OrderResponse {

    private Long id;
    private CustomerResponse customer;
    private Buses buses;
    private String pickUpLocation;
    private Integer quantity;
    private Double totalPrice;
    private Date paymentBeforeDate;
    private Boolean statusPayment;

}
