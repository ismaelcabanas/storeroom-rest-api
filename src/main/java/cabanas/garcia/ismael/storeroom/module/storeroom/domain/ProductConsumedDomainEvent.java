package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;

import java.util.Objects;

public class ProductConsumedDomainEvent extends DomainEvent {
  private final String productId;
  private final Integer quantity;

  private ProductConsumedDomainEvent(Builder builder) {
    this.productId = builder.productId;
    this.quantity = builder.quantity;
  }

  public static Builder builder() {
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

    ProductConsumedDomainEvent that = (ProductConsumedDomainEvent) o;
    return Objects.equals(productId, that.productId)
            && Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {

    return Objects.hash(productId, quantity);
  }

  public static final class Builder {
    private String productId;
    private Integer quantity;

    private Builder() {
    }

    public Builder withProductId(String val) {
      this.productId = val;
      return this;
    }

    public Builder withQuantity(Integer val) {
      this.quantity = val;
      return this;
    }

    public ProductConsumedDomainEvent build() {
      return new ProductConsumedDomainEvent(this);
    }
  }
}
