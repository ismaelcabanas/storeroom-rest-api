package cabanas.garcia.ismael.storeroom.module.storeroom.application.consume;

import java.util.Objects;

public final class ConsumeProductCommand {
  private final String productId;
  private final Integer quantity;
  private final String storeroomId;

  private ConsumeProductCommand(Builder builder) {
    this.productId = builder.productId;
    this.quantity = builder.quantity;
    this.storeroomId = builder.storeroomId;
  }

  public String getProductId() {
    return productId;
  }

  public Integer getQuantity() {
    return quantity;
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
    ConsumeProductCommand that = (ConsumeProductCommand) o;
    return Objects.equals(productId, that.productId)
            && Objects.equals(quantity, that.quantity)
            && Objects.equals(storeroomId, that.storeroomId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(productId, quantity, storeroomId);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String productId;
    private Integer quantity;
    private String storeroomId;

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

    public Builder withStoreroomId(String val) {
      this.storeroomId = val;
      return this;
    }

    public ConsumeProductCommand build() {
      return new ConsumeProductCommand(this);
    }
  }
}
