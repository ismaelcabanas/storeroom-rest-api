package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = NewProductRequest.Builder.class)
public class NewProductRequest {

  private final String name;

  private NewProductRequest(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static Builder newProductRequest() {
    return new Builder();
  }

  public static final class Builder {
    private String name;

    private Builder() {
    }

    public Builder withName(String productName) {
      this.name = productName;
      return this;
    }

    public NewProductRequest build() {
      return new NewProductRequest(this);
    }
  }
}
