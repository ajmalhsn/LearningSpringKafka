package com.spring.kafka.LearningSpringKafka.service;

import com.spring.kafka.LearningSpringKafka.dto.OrderEvent;
import com.spring.kafka.LearningSpringKafka.entity.OrderEntity;
import com.spring.kafka.LearningSpringKafka.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderRepository repository;
    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "${app.kafka.topic}", groupId = "springkafka-group")
    public void consume(OrderEvent event) {
        OrderEntity entity = new OrderEntity();
        entity.setOrderId(event.getOrderId());
        entity.setProduct(event.getProduct());
        entity.setQuantity(event.getQuantity());
        entity.setAmount(event.getAmount());
        entity.setStatus("PROCESSED");
        entity.setTimestamp(event.getTimestamp());
        repository.save(entity);

        // push to any subscribed React clients in real time
        messagingTemplate.convertAndSend("/topic/orders", entity);
    }
}