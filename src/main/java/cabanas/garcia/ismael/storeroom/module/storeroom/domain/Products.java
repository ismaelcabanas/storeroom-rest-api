package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class Products extends ValueObject<Products> {
  public static final Products EMPTY = new Products();

  private final Set<Product> products;

  public Products() {
    this.products = new HashSet<>();
  }

  private Products(Set<Product> products) {
    this.products = products;
  }

  public boolean contains(Product product) {
    return products.contains(product);
  }

  public Products add(Product newProduct) {
    Preconditions.checkNotNull(newProduct, "Product should not be null");

    products.add(newProduct);
    return new Products(new HashSet<>(products));
  }

  @Override
  protected boolean sameValueAs(Products other) {
    return equals(other);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()){
      return false;
    }

    Products other = (Products) o;

    return products.size() == other.products.size() && products.containsAll(other.products);
  }

  @Override
  public int hashCode() {

    return Objects.hash(products);
  }

}
