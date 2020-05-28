package com.kaya.shoppingcartservice.controller;

import com.kaya.shoppingcartservice.entity.Product;
import com.kaya.shoppingcartservice.entity.ShoppingCart;
import com.kaya.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shopping-cart")
public class ShoppingCartController {

  @Autowired ShoppingCartService shoppingCartService;

  @PostMapping
  public ResponseEntity<ShoppingCart> create(@RequestParam("name") String name) {
    return shoppingCartService.create(name);
  }

  @PostMapping("{id}")
  public ResponseEntity<ShoppingCart> addProducts(
      @PathVariable("id") Long shoppingCartId, @RequestBody List<Product> products) {
    return shoppingCartService.addProducts(shoppingCartId, products);
  }

  @GetMapping("{id}")
  public ResponseEntity<Map<String, String>> getShoppingCartPrice(
      @PathVariable("id") Long shoppingCartId) {
    return shoppingCartService.getShoppingCartPrice(shoppingCartId);
  }
}
