package com.example.java_training.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
public class ProductRequest {
    @NotNull(message = "Product name cannot empty")
    private String productName;
    private String productDescription;
    @PositiveOrZero(message = "Product price greater than or equal to 0")
    private long productPrice;
    private int stock;

}
