package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ProductCreatedResponse.Builder.class)
public class ProductCreatedResponse {
  private final String name;

  private ProductCreatedResponse(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static Builder productCreatedResponse() {
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