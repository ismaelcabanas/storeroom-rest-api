package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ProductCreatedResponse.Builder.class)
public class ProductCreatedResponse {
  private final String name;
  private final String id;

  private ProductCreatedResponse(Builder builder) {
    this.name = builder.name;
    this.id = builder.id;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static Builder productCreatedResponse() {
    return new Builder();
  }

  public static final class Builder {
    private String name;
    private String id;

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

    public ProductCreatedResponse build() {
      return new ProductCreatedResponse(this);
    }
  }
}