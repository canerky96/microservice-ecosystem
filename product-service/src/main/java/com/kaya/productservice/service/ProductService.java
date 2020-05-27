package com.kaya.productservice.service;

import com.kaya.productservice.entity.Product;
import com.kaya.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired ProductRepository productRepository;

  public ResponseEntity<Product> queryById(Long id) {
    Product product =
        productRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Verilen id ile eşlenen sonuç bulunamadı"));
    return ResponseEntity.ok().body(product);
  }

  public ResponseEntity<List<Product>> getAll() {
    return ResponseEntity.ok().body(productRepository.findAll());
  }

  public ResponseEntity<Product> save(Product product) {
    return ResponseEntity.ok().body(productRepository.save(product));
  }
}
