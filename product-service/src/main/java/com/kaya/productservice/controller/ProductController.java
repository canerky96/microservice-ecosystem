package com.kaya.productservice.controller;

import com.kaya.productservice.entity.Product;
import com.kaya.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

  @Autowired ProductService productService;

  @GetMapping("{id}")
  public ResponseEntity<Product> queryById(@PathVariable("id") Long id) {
    return productService.queryById(id);
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAll() {
    return productService.getAll();
  }

  @PostMapping
  public ResponseEntity<Product> save(@RequestBody Product product) {
    return productService.save(product);
  }
}
