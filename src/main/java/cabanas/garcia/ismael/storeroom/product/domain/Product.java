package cabanas.garcia.ismael.storeroom.product.domain;

import java.util.Objects;

public final class Product {
  private final ProductName productName;
  private final ProductId productId;

  private Product(Builder builder) {
    this.productId = builder.productId;
    this.productName = builder.productName;
  }

  public static Builder product() {
    return new Builder();
  }

  public String getId() {
    return productId.getId();
  }

  public String getName() {
    return productName.getName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(productId, product.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }

  public static final class Builder {
    private ProductName productName;
    private ProductId productId;

    private Builder() {
    }

    public Builder withName(ProductName val) {
      this.productName = val;
      return this;
    }

    public Builder withId(ProductId val) {
      this.productId = val;
      return this;
    }

    public Product build() {
      return new Product(this);
    }
  }
}