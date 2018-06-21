package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.response;

import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = ProductCreatedResponse.Builder.class)
public class ProductCreatedResponse {
  private final ProductName productName;

  private ProductCreatedResponse(Builder builder) {
    this.productName = builder.productName;
  }

  public String getName() {
    return productName.getName();
  }

  public static Builder productCreatedResponse() {
    return new Builder();
  }

  public static final class Builder {
    private ProductName productName;

    private Builder() {
    }

    public Builder withName(ProductName val) {
      this.productName = val;
      return this;
    }

    public ProductCreatedResponse build() {
      return new ProductCreatedResponse(this);
    }
  }
}
