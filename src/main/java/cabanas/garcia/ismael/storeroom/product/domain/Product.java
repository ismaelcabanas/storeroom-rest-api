package cabanas.garcia.ismael.storeroom.product.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;

import java.util.Objects;

public final class Product extends AggregateRoot<Product, ProductId> {
  private final ProductName productName;
  private final ProductId productId;

  private Product(Builder builder) {
    this.productId = builder.productId;
    this.productName = builder.productName;
  }

  public static Builder product() {
    return new Builder();
  }

  public ProductId getId() {
    return productId;
  }

  @Override
  protected boolean sameIdentityAs(Product other) {
    return equals(other);
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

  public static Product create(ProductId productId, ProductName productName) {
    Product product = Product.product()
            .withId(productId)
            .withName(productName)
            .build();

    product.record(ProductCreatedDomainEvent.builder()
            .withId(product.getId().getId())
            .withName(product.getName())
            .build());

    return product;
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