package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public final class Size extends ValueObject<Size> {
  private final int value;

  public Size(int value) {
    super();
    this.value = value;
  }

  @Override
  protected boolean sameValueAs(final Size other) {
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
    Size size = (Size) o;
    return value == size.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
            .append("value", value)
            .toString();
  }
}
