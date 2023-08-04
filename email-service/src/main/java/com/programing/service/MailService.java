package com.programing.service;

import com.programing.model.response.OrderResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @KafkaListener(topics = "send-mail", groupId = "send-mail-id")
    public void sendMail(OrderResponse order) {
        System.out.println(order.toString());
    }

}
