package cabanas.garcia.ismael.shared.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public abstract class Id<T, K> extends ValueObject<T> {

  protected final K value;

  public Id(K value) {
    Preconditions.checkNotNull(value, "Id not be should null");

    this.value = value;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    cabanas.garcia.ismael.shared.domain.Id that = (cabanas.garcia.ismael.shared.domain.Id) o;

    return Objects.equals(value, that.value);
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

  @Override
  public boolean sameValueAs(final T other) {
    return equals(other);
  }

  public K getValue() {
    return value;
  }
}