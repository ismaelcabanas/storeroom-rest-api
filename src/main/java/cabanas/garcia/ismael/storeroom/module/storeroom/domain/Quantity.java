package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Quantity extends ValueObject<Quantity> {
  private int value;

  private Quantity(Builder builder) {
    this.value = builder.value;
  }

  public int getValue() {
    return value;
  }

  @Override
  protected boolean sameValueAs(final Quantity other) {
    return equals(other);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Quantity quantity = (Quantity) o;

    return value == quantity.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("value", value)
            .toString();
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private int value;

    public Builder withValue(int val) {
      this.value = val;
      return this;
    }

    public Quantity build() {
      return new Quantity(this);
    }
  }
}
