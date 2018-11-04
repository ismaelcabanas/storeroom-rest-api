package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.HashSet;
import java.util.Set;

public final class Products {
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
    products.add(newProduct);
    return new Products(new HashSet<>(products));
  }
}
