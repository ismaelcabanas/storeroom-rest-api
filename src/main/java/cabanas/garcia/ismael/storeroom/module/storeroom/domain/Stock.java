package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public final class Stock extends ValueObject<Stock> {
  private static final int ZERO = 0;

  private final int value;

  private Stock(Builder builder) {
    this.value = builder.value;
  }

  public int getValue() {
    return value;
  }

  @Override
  protected boolean sameValueAs(Stock other) {
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

    Stock stock = (Stock) o;

    return value == stock.value;
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

  public static Stock empty() {
    return Stock.builder().withValue(ZERO).build();
  }

  public Stock add(Quantity quantity) {
    return Stock.builder().withValue(value + quantity.getValue()).build();
  }

  public Stock decrease(Quantity quantity) {
    if (quantity.getValue() > this.value) {
      throw new StockOverflowException();
    }
    return Stock.builder().withValue(value - quantity.getValue()).build();
  }

  public static final class Builder {
    private int value;

    public Builder withValue(int val) {
      this.value = val;
      return this;
    }

    public Stock build() {
      return new Stock(this);
    }
  }
}
