package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;

import java.util.Objects;

public final class Stock extends ValueObject<Stock> {

  public static final Stock ZERO = new Stock(0);

  private final int value;

  public Stock(int value) {
    this.value = value;
  }

  @Override
  protected boolean sameValueAs(final Stock other) {
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
}
