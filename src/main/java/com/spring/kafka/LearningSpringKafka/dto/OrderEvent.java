package com.spring.kafka.LearningSpringKafka.dto;


import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String orderId;
    private String product;
    private int quantity;
    private BigDecimal amount;
    private String status;      // CREATED, PROCESSING, COMPLETED
    private Instant timestamp
}