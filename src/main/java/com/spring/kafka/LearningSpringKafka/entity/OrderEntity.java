package com.spring.kafka.LearningSpringKafka.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity {
    @Id
    private String orderId;
    private String product;
    private int quantity;
    private BigDecimal amount;
    private String status;
    private Instant timestamp;
}