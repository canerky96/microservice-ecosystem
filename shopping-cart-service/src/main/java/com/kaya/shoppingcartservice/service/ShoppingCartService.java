package com.kaya.shoppingcartservice.service;

import com.kaya.shoppingcartservice.entity.Product;
import com.kaya.shoppingcartservice.entity.ShoppingCart;
import com.kaya.shoppingcartservice.repository.ProductRepository;
import com.kaya.shoppingcartservice.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShoppingCartService {

  @Autowired ShoppingCartRepository shoppingCartRepository;
  @Autowired ProductRepository productRepository;

  public ResponseEntity<ShoppingCart> create(String name) {
    ShoppingCart shoppingCart = new ShoppingCart();
    shoppingCart.setShoppincartName(name);
    return ResponseEntity.ok().body(shoppingCartRepository.save(shoppingCart));
  }

  public ResponseEntity<ShoppingCart> addProducts(Long shoppingCartId, List<Product> products) {
    ShoppingCart shoppingCart =
        shoppingCartRepository
            .findById(shoppingCartId)
            .orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç bulunamadı"));

    products.forEach(product -> productRepository.saveAndFlush(product));

    Set<Product> newProducts = new HashSet<>(products);
    shoppingCart.setProducts(newProducts);
    return ResponseEntity.ok().body(shoppingCartRepository.save(shoppingCart));
  }
}
