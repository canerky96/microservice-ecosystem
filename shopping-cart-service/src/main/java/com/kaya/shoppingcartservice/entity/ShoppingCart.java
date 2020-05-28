package com.kaya.shoppingcartservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class ShoppingCart {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String shoppincartName;

  @ManyToMany
  @JoinTable(
      name = "shopping_cart_product",
      joinColumns = @JoinColumn(name = "shopping_cart_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  Set<Product> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShoppincartName() {
    return shoppincartName;
  }

  public void setShoppincartName(String shoppincartName) {
    this.shoppincartName = shoppincartName;
  }

  public Set<Product> getProducts() {
    return products;
  }

  public void setProducts(Set<Product> products) {
    this.products = products;
  }
}
