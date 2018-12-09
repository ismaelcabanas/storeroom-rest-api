package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@JsonDeserialize(builder = ConsumeProductRequestBody.Builder.class)
public class ConsumeProductRequestBody {
  private final Integer quantity;

  private ConsumeProductRequestBody(Builder builder) {
    this.quantity = builder.quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ConsumeProductRequestBody that = (ConsumeProductRequestBody) o;
    return Objects.equals(quantity, that.quantity);
  }

  @Override
  public int hashCode() {

    return Objects.hash(quantity);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("quantity", quantity)
            .toString();
  }

  public static Builder builder() {
    return new Builder();
  }

  public Integer getQuantity() {
    return quantity;
  }

  public static final class Builder {
    private Integer quantity;

    private Builder() {
    }

    public Builder withQuantity(Integer val) {
      this.quantity = val;
      return this;
    }

    public ConsumeProductRequestBody build() {
      return new ConsumeProductRequestBody(this);
    }
  }
}
