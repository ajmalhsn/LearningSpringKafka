package com.spring.kafka.LearningSpringKafka.controller;

import com.spring.kafka.LearningSpringKafka.dto.OrderEvent;
import com.spring.kafka.LearningSpringKafka.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.UUID;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from the React frontend
public class OrderController {

    private final OrderProducer producer;

    @PostMapping
    public OrderEvent createOrder(@RequestBody OrderEvent request) {
        request.setOrderId(UUID.randomUUID().toString());
        request.setStatus("CREATED");
        request.setTimestamp(Instant.now());
        producer.publish(request);
        return request; // 202-style ack; the real processing happens async via Kafka
    }
}