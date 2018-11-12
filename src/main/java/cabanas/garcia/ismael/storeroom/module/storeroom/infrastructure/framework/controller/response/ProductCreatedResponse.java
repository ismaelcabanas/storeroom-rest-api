package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ProductCreatedResponse.Builder.class)
public final class ProductCreatedResponse {
  private final String name;
  private final Integer stock;

  private ProductCreatedResponse(Builder builder) {
    this.name = builder.name;
    this.stock = builder.stock;
  }

  public String getName() {
    return name;
  }

  public Integer getStock() {
    return stock;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String name;
    private Integer stock;

    private Builder() {
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public ProductCreatedResponse build() {
      return new ProductCreatedResponse(this);
    }

    public Builder withStock(Integer val) {
      this.stock = val;
      return this;
    }
  }
}
