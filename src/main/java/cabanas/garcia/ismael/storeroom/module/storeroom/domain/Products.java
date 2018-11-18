package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class Products extends ValueObject<Products> {
  private final Set<Product> productSet;

  public Products() {
    this.productSet = new HashSet<>();
  }

  private Products(Set<Product> productSet) {
    this.productSet = productSet;
  }

  public boolean contains(Product product) {
    return productSet.contains(product);
  }

  public Products add(Product newProduct) {
    Preconditions.checkNotNull(newProduct, "Product should not be null");

    newProduct.add();
    productSet.add(newProduct);
    return new Products(new HashSet<>(productSet));
  }

  public Product find(ProductId id) {
    return productSet.stream()
            .filter(p -> p.id().equals(id))
            .findFirst()
            .orElseThrow(() -> new ProductNotInStoreroomException(id));
  }

  public Set<Product> getProducts() {
    return productSet;
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

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Products other = (Products) o;

    return productSet.size() == other.productSet.size() && productSet.containsAll(other.productSet);
  }

  @Override
  public int hashCode() {

    return Objects.hash(productSet);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("productSet", productSet)
            .toString();
  }

}
