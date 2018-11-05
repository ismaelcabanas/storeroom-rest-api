package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;

import java.util.Objects;

public class ProductName extends ValueObject<ProductName> {
  private final String name;

  public ProductName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  protected boolean sameValueAs(final ProductName other) {
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

    ProductName that = (ProductName) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
