package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class Product extends AggregateRoot<Product, ProductId> {
  private final ProductName name;
  private final ProductId id;
  private TrackingState state;

  private Product(Builder builder) {
    Preconditions.checkNotNull(builder.id, "ProductId should not be null");
    Preconditions.checkNotNull(builder.name, "ProductName should not be null");
    this.name = builder.name;
    this.id = builder.id;
    this.state = TrackingState.UNCHANGED;
  }

  public static Builder builder() {
    return new Builder();
  }

  public ProductName getName() {
    return name;
  }

  public TrackingState getState() {
    return state;
  }

  @Override
  public ProductId getId() {
    return id;
  }

  public void add() {
    this.state = TrackingState.ADDED;
  }

  @Override
  protected boolean sameIdentityAs(final Product other) {
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
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public static final class Builder {
    private ProductName name;
    private ProductId id;

    private Builder() {
    }

    public Builder withName(ProductName val) {
      this.name = val;
      return this;
    }

    public Builder withId(ProductId val) {
      this.id = val;
      return this;
    }

    public Product build() {
      return new Product(this);
    }

  }
}
