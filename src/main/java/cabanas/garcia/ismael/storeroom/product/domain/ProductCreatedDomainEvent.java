package cabanas.garcia.ismael.storeroom.product.domain;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;

import java.util.Objects;

public final class ProductCreatedDomainEvent extends DomainEvent {
  private final String id;
  private final String name;

  private ProductCreatedDomainEvent(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductCreatedDomainEvent that = (ProductCreatedDomainEvent) o;
    return Objects.equals(id, that.id)
            && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String id;
    private String name;

    private Builder() {
    }

    public Builder withId(String val) {
      this.id = val;
      return this;
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public ProductCreatedDomainEvent build() {
      return new ProductCreatedDomainEvent(this);
    }
  }
}
