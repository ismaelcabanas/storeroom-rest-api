package cabanas.garcia.ismael.shared.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public abstract class Id<TYPE, Id> extends ValueObject<TYPE> {

  protected final Id id;

  public Id(Id id) {
    Preconditions.checkNotNull(id, "Id not be should null");

    this.id = id;
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

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .toString();
  }

  @Override
  public boolean sameValueAs(final TYPE other) {
    return equals(other);
  }

  public Id getValue() {
    return id;
  }
}