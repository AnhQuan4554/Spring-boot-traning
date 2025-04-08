package com.example.java_training.entity;

import com.example.java_training.base.Auditable;
import com.example.java_training.dto.request.ProductRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private String productDescription;

    @Column
    private long productPrice;

    @Column
    private int stock;

    public ProductEntity() {}

    public ProductEntity(ProductRequest productRequest) {
        BeanUtils.copyProperties(productRequest, this);
    }
}
