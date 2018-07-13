package cabanas.garcia.ismael.storeroom.product.domain;

public class Product {
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