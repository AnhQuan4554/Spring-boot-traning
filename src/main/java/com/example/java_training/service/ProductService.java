package com.example.java_training.service;

import com.example.java_training.dto.request.ProductRequest;
import com.example.java_training.entity.ProductEntity;
import com.example.java_training.repository.ProductRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // Get list
    public Page<ProductEntity> getListProduct(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDir), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    // Creat
    public ResponseEntity<Object> creatProduct(ProductEntity productEntity) {
       try {
           ProductEntity newProduct = productRepository.save(productEntity);
           return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
       }catch (Exception e) {
           System.out.println(e.getMessage());
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Create product failed");
       }
    }

    // Update
    public ResponseEntity<Object> updateProduct(ProductEntity productEntity,long id) {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        BeanUtils.copyProperties(productEntity, existingProduct, "id");
        ProductEntity updatedProduct = productRepository.save(existingProduct);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }
}
