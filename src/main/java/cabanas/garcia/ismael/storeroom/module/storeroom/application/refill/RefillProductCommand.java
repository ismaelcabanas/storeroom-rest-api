package cabanas.garcia.ismael.storeroom.module.storeroom.application.refill;

public final class RefillProductCommand {
  private final String productId;
  private final Integer quantity;
  private final String storeroomId;

  private RefillProductCommand(Builder builder) {
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

    public RefillProductCommand build() {
      return new RefillProductCommand(this);
    }
  }
}
