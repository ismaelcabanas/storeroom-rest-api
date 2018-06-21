package cabanas.garcia.ismael.storeroom.product.domain;

import java.util.Objects;

public final class ProductName {
  private final String name;

  private ProductName(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static Builder productName() {
    return new Builder();
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

  @Override
  public String toString() {
    return "ProductName{"
            + "name='" + name + '\''
            + '}';
  }

  public static final class Builder {
    private String name;

    private Builder() {
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public ProductName build() {
      return new ProductName(this);
    }
  }
}
