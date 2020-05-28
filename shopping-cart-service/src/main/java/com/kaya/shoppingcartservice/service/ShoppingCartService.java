package com.kaya.shoppingcartservice.service;

import com.kaya.shoppingcartservice.entity.Product;
import com.kaya.shoppingcartservice.entity.ShoppingCart;
import com.kaya.shoppingcartservice.repository.ProductRepository;
import com.kaya.shoppingcartservice.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ShoppingCartService {

  @Autowired ShoppingCartRepository shoppingCartRepository;
  @Autowired ProductRepository productRepository;
  @Autowired RestTemplate restTemplate;

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

  public ResponseEntity<Map<String, String>> getShoppingCartPrice(Long shoppingCartId) {
    Map<String, String> response = new HashMap<>();

    ShoppingCart shoppingCart =
        shoppingCartRepository
            .findById(shoppingCartId)
            .orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç bulunamadı"));

    int totalPrice =
        shoppingCart.getProducts().stream()
            .map(
                product ->
                    restTemplate.getForObject(
                        "http://product-service/product/" + product.getId(), HashMap.class))
            .mapToInt(productResponse -> (int) productResponse.get("price"))
            .sum();

    response.put("Total Price", Integer.toString(totalPrice));
    return ResponseEntity.ok().body(response);
  }
}
