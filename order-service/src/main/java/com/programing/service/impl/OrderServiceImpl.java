package com.programing.service.impl;

import com.programing.entity.Customer;
import com.programing.entity.Order;
import com.programing.exception.BadRequestException;
import com.programing.exception.NotFoundException;
import com.programing.model.Buses;
import com.programing.model.UpdateQuantityTicket;
import com.programing.model.request.OrderRequest;
import com.programing.model.response.CustomerResponse;
import com.programing.model.response.OrderResponse;
import com.programing.repository.CustomerRepository;
import com.programing.repository.OrderRepository;
import com.programing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, UpdateQuantityTicket> kafkaTemplate;
    private final KafkaTemplate<String, OrderResponse> orderKafkaTemplate;
    @Override
    public Map<String, Object> getAll(Boolean statusPayment, Pageable pageable) {
        Page<Order> orderPage = null;
        if(statusPayment!=null) {
            orderPage = orderRepository.findAllByStatusPayment(statusPayment, pageable);
        } else orderPage = orderRepository.findAll(pageable);

        List<OrderResponse> orderResponses = orderPage.getContent().stream().map(this::toOrderResponse).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", orderResponses);
        response.put("currentPage", orderPage.getNumber());
        response.put("totalItems", orderPage.getTotalElements());
        response.put("totalPages", orderPage.getTotalPages());

        return response;
    }

    private OrderResponse toOrderResponse(Order order) {
        String busesUriRequest = "http://car-service/api/buses/" + order.getBusesId();
        Buses buses = null;

        try {
            buses = webClientBuilder.build().get()
                    .uri(busesUriRequest)
                    .retrieve()
                    .bodyToMono(Buses.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(buses==null) throw new NotFoundException(404, "ID buses " + order.getBusesId() + " is not found!");

        return OrderResponse.builder()
                .id(order.getId())
                .customer(toCustomerResponse(order.getCustomer()))
                .buses(buses)
                .pickUpLocation(order.getPickUpLocation())
                .quantity(order.getQuantity())
                .totalPrice(order.getTotalPrice())
                .paymentBeforeDate(order.getPaymentBeforeDate())
                .statusPayment(order.getStatusPayment())
                .build();
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
    public OrderResponse getDetail(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException(404, "ID is not found!");
        });

        return toOrderResponse(order);
    }

    @Override
    public void create(OrderRequest request) {
        if(request.getPhoneNumber()==null || request.getEmail()==null || request.getFullName()==null ||
            request.getBusesId()==null || request.getPickUpLocation()==null || request.getQuantity()==null) {
            throw new BadRequestException(400, "Input full info, please.");
        }

        String busesUriRequest = "http://car-service/api/buses/" + request.getBusesId();
        Buses buses = null;
        try {
            buses = webClientBuilder.build().get()
                    .uri(busesUriRequest)
                    .retrieve()
                    .bodyToMono(Buses.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(buses==null)
            throw new NotFoundException(404, "ID buses " + request.getBusesId() + " is not found!");

        if(buses.getNumberOfSeats() < request.getQuantity())
            throw new BadRequestException(404, "Tickets are not enough!");

        Customer customer = null;
        if(customerRepository.existsByEmail(request.getEmail())) {
            customer = customerRepository.findByEmail(request.getEmail());
        } else customer = customerRepository.save(Customer.builder()
                                                        .numberPhone(request.getPhoneNumber())
                                                        .email(request.getEmail())
                                                        .fullName(request.getFullName())
                                                        .build());

        Date today = new Date();
        Order order = Order.builder()
                .customer(customer)
                .busesId(buses.getId())
                .pickUpLocation(request.getPickUpLocation())
                .quantity(request.getQuantity())
                .totalPrice(buses.getTicketPrice()*request.getQuantity())
                .statusPayment(false)
                .paymentBeforeDate(new Date(today.getTime() + (3000 * 60 * 60 * 24)))
                .build();

        orderRepository.save(order);

        UpdateQuantityTicket updateQuantityTicket = new UpdateQuantityTicket();
        updateQuantityTicket.setId(order.getBusesId());
        updateQuantityTicket.setQuantity(order.getQuantity());

        Message<UpdateQuantityTicket> messageCreateTicket = MessageBuilder
                .withPayload(updateQuantityTicket)
                .setHeader(KafkaHeaders.TOPIC, "create-order")
                .build();
        kafkaTemplate.send(messageCreateTicket);

        Message<OrderResponse> messageOrder = MessageBuilder
                .withPayload(toOrderResponse(order))
                .setHeader(KafkaHeaders.TOPIC, "send-mail")
                .build();
        orderKafkaTemplate.send(messageOrder);
    }

    @Override
    public void update(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException(404, "ID is not found!");
        });

        Customer customer = order.getCustomer();
        if(request.getEmail()!=null) {

            customer.setEmail(request.getEmail());
        }
        if(request.getPhoneNumber()!=null) {
            customer.setNumberPhone(request.getPhoneNumber());
        }
        if(request.getFullName()!=null) {
            customer.setFullName(request.getFullName());
        }

        if(null!=request.getPickUpLocation()) {
            order.setPickUpLocation(request.getPickUpLocation());
        }

        Buses buses = null;
        String busesUriRequest = "http://car-service/api/buses/" + order.getBusesId();
        if(null!=request.getBusesId())
            busesUriRequest = "http://car-service/api/buses/" + request.getBusesId();

        try {
            buses = webClientBuilder.build().get()
                    .uri(busesUriRequest)
                    .retrieve()
                    .bodyToMono(Buses.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(buses==null)
            throw new NotFoundException(404, "ID buses " + request.getBusesId() + " is not found!");

        if(null!=request.getQuantity()) {
            order.setQuantity(request.getQuantity());
            order.setTotalPrice(buses.getTicketPrice()*order.getQuantity());
        }
        if(null!=request.getBusesId()) {
            order.setBusesId(buses.getId());
            order.setTotalPrice(buses.getTicketPrice()*order.getQuantity());
        }

        customerRepository.save(customer);
        orderRepository.save(order);
    }

    @Override
    public void updateStatusPay(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException(404, "ID is not found!");
        });

        order.setStatusPayment(!order.getStatusPayment());
        orderRepository.save(order);
    }

    @Override
    public void cancel(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()->{
            throw new NotFoundException(404, "ID is not found!");
        });

        orderRepository.delete(order);

        UpdateQuantityTicket updateQuantityTicket = new UpdateQuantityTicket();
        updateQuantityTicket.setId(id);
        updateQuantityTicket.setQuantity(order.getQuantity());

        Message<UpdateQuantityTicket> message = MessageBuilder
                .withPayload(updateQuantityTicket)
                .setHeader(KafkaHeaders.TOPIC, "cancel-order")
                .build();

        kafkaTemplate.send(message);
    }
}
