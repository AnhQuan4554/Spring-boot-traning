package com.example.java_training.entity;

import com.example.java_training.base.Auditable;
import com.example.java_training.enums.PaymentStatus;
import com.example.java_training.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order")
@Getter
@Setter
public class OrderEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String orderCode;

    @NotNull
    @Column
    private Long userId;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Column
    private Double totalPrice;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
