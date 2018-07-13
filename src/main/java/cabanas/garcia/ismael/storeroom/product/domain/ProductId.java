package cabanas.garcia.ismael.storeroom.product.domain;

import java.util.Objects;

public class ProductId {
  private String id;

  private ProductId(Builder builder) {
    this.id = builder.id;
  }

  public String getId() {
    return id;
  }

  public static Builder productId() {
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
    ProductId productId = (ProductId) o;
    return Objects.equals(id, productId.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "ProductId{"
            + "id='" + id + '\''
            + '}';
  }

  public static final class Builder {
    private String id;

    private Builder() {
    }

    public Builder withId(String val) {
      this.id = val;
      return this;
    }

    public ProductId build() {
      return new ProductId(this);
    }
  }
}