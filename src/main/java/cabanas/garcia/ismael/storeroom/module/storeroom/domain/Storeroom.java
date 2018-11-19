package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public final class Storeroom extends AggregateRoot<Storeroom, StoreroomId> {

  private final StoreroomName name;
  private final StoreroomId id;
  private Products products;

  private Storeroom(Builder builder) {
    Preconditions.checkNotNull(builder.id, "StoreroomId should not be null");
    Preconditions.checkNotNull(builder.name, "StoreroomName should not be null");
    this.name = builder.name;
    this.id = builder.id;
    this.products = new Products();
  }

  public static Builder builder() {
    return new Builder();
  }

  public StoreroomName name() {
    return name;
  }

  @Override
  public StoreroomId id() {
    return id;
  }

  public Product addProduct(Product product) {
    this.products = products.add(product);
    record(ProductAddedDomainEvent.builder()
            .withId(product.id().getValue())
            .withName(product.name().getName())
            .withStoreroomId(this.id.getValue())
            .build());
    return product;
  }

  public Product reFill(Product product, Quantity quantity) {
    Product productToRefill = this.products.find(product.id());
    productToRefill.addStock(quantity);
    return productToRefill;
  }


  public Products products() {
    return products;
  }

  public boolean contains(Product product) {
    return products.contains(product);
  }

  @Override
  protected boolean sameIdentityAs(final Storeroom other) {
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
    Storeroom storeroom = (Storeroom) o;
    return Objects.equals(id, storeroom.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("name", name)
            .append("id", id)
            .append("products", products)
            .toString();
  }

  public static final class Builder {
    private StoreroomName name;
    private StoreroomId id;

    private Builder() {
    }

    public Builder withName(StoreroomName val) {
      this.name = val;
      return this;
    }

    public Builder withId(StoreroomId val) {
      this.id = val;
      return this;
    }

    public Storeroom build() {
      return new Storeroom(this);
    }

  }
}