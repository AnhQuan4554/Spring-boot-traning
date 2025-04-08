package com.example.java_training.controller;


import com.example.java_training.dto.request.ProductRequest;
import com.example.java_training.entity.ProductEntity;
import com.example.java_training.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductEntity>> getAllProduct
            (@RequestParam(defaultValue = "0") int pageNo,
             @RequestParam(defaultValue = "10") int pageSize,
             @RequestParam(defaultValue = "id") String sortBy,
             @RequestParam(defaultValue = "asc") String sortDir) {
        Page<ProductEntity> products = productService.getListProduct(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(products);
    }

    // Create product
    @PostMapping
    public ResponseEntity<Object> addProduct(@RequestBody ProductRequest productRequest) {
        ProductEntity newProduct = new ProductEntity(productRequest);
        return productService.creatProduct(newProduct);
    }
}
