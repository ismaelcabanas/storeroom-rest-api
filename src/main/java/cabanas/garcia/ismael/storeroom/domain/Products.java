package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import cabanas.garcia.ismael.storeroom.product.domain.Product;

import java.util.ArrayList;
import java.util.List;

public final class Products extends ValueObject<Products> {

  public static final Products EMPTY = new Products();
  private final List<Product> products;

  private Products() {
    this.products = new ArrayList<>();
  }

  public Products(List<Product> products) {
    this.products = products;
  }

  @Override
  protected boolean sameValueAs(Products other) {
    return false;
  }

  public boolean isEmpty() {
    return products.size() == 0;
  }
}
