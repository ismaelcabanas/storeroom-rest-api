package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;

import java.util.ArrayList;
import java.util.List;

public final class Products extends ValueObject<Products> {

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

  public Size size() {
    return new Size(products.size());
  }

  public Products add(final Product product) {
    if (exist(product)) {
      throw new ProductAlreadyExistException(product.getId());
    } else {
      products.add(product);
      return new Products(new ArrayList<>(this.products));
    }
  }

  public static Products empty() {
    return new Products();
  }

  private boolean exist(final Product product) {
    return this.products.stream().anyMatch(prod -> prod.equals(product));
  }

  public Product get(ProductId productId) {
    return products.stream()
            .filter(product -> product.getId().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ProductNotExistException(productId));
  }
}
