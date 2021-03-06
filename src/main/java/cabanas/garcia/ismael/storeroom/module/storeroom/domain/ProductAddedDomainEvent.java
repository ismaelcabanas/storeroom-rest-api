package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;

import java.util.Objects;

public final class ProductAddedDomainEvent extends DomainEvent {
  private final String id;
  private final String name;
  private final String storeroomId;

  private ProductAddedDomainEvent(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.storeroomId = builder.storeroomId;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getStoreroomId() {
    return storeroomId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductAddedDomainEvent that = (ProductAddedDomainEvent) o;
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
    private String storeroomId;

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

    public ProductAddedDomainEvent build() {
      return new ProductAddedDomainEvent(this);
    }

    public Builder withStoreroomId(String val) {
      this.storeroomId = val;
      return this;
    }
  }
}
