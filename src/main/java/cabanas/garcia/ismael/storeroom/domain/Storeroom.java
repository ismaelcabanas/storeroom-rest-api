package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;
import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import com.google.common.base.Preconditions;

public final class Storeroom extends AggregateRoot<Storeroom, StoreroomId> {

  private final StoreroomName name;
  private final StoreroomId id;
  private Products products;

  private Storeroom(Builder builder) {
    Preconditions.checkNotNull(builder.id, "StoreroomId should not be null");
    Preconditions.checkNotNull(builder.name, "StoreroomName should not be null");
    this.name = builder.name;
    this.id = builder.id;
    this.products = Products.empty();
  }

  public static Builder builder() {
    return new Builder();
  }

  public StoreroomName getName() {
    return name;
  }

  @Override
  protected StoreroomId getId() {
    return id;
  }

  @Override
  protected boolean sameIdentityAs(Storeroom other) {
    if (other != null) {
      if (other.id == null && id == null) {
        return true;
      } else if (id != null) {
        return id.sameValueAs(other.id);
      }
    }

    return false;
  }

  public boolean isEmpty() {
    return products.isEmpty();
  }

  public Size productsSize() {
    return products.size();
  }

  public void addProduct(Product product) {
    this.products = products.add(product);
  }

  public Product product(ProductId productId) {
    return products.get(productId);
  }

  public Stock productStock(ProductId productId) {
    return null;
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
