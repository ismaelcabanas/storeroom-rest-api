package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response;

public final class ProductCreatedResponse {
  private final String name;

  private ProductCreatedResponse(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String name;

    private Builder() {
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public ProductCreatedResponse build() {
      return new ProductCreatedResponse(this);
    }
  }
}
