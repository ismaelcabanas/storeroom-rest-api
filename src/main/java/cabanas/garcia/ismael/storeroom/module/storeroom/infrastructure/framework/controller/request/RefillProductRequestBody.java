package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonDeserialize(builder = RefillProductRequestBody.Builder.class)
public class RefillProductRequestBody {
  private final Integer quantity;

  private RefillProductRequestBody(Builder builder) {
    this.quantity = builder.quantity;
  }

  public Integer getQuantity() {
    return quantity;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("quantity", quantity)
            .toString();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private Integer quantity;

    private Builder() {
    }

    public Builder withQuantity(Integer val) {
      this.quantity = val;
      return this;
    }

    public RefillProductRequestBody build() {
      return new RefillProductRequestBody(this);
    }
  }
}
