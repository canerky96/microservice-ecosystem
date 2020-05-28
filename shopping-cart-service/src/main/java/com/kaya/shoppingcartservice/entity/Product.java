package com.kaya.shoppingcartservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Product {

  @Id private Long id;

  private String name;

  @JsonIgnore
  @ManyToMany(mappedBy = "products")
  private Set<ShoppingCart> shoppingCarts;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<ShoppingCart> getShoppingCarts() {
    return shoppingCarts;
  }

  public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
    this.shoppingCarts = shoppingCarts;
  }
}
