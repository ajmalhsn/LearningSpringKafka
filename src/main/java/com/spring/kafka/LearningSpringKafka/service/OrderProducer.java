package com.spring.kafka.LearningSpringKafka.service;

import com.spring.kafka.LearningSpringKafka.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topic;

    public void publish(OrderEvent event) {
        // key = orderId ensures all events for the same order land on the same partition,
        // preserving per-order ordering
        kafkaTemplate.send(topic, event.getOrderId(), event);
    }
}