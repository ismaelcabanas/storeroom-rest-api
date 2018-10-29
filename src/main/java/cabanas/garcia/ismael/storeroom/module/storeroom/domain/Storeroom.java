package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;
import com.google.common.base.Preconditions;

import java.util.Objects;

public final class Storeroom extends AggregateRoot<Storeroom, StoreroomId> {

  private final StoreroomName name;
  private final StoreroomId id;

  private Storeroom(Builder builder) {
    Preconditions.checkNotNull(builder.id, "StoreroomId should not be null");
    Preconditions.checkNotNull(builder.name, "StoreroomName should not be null");
    this.name = builder.name;
    this.id = builder.id;
  }

  public static Builder builder() {
    return new Builder();
  }

  public StoreroomName getName() {
    return name;
  }

  @Override
  public StoreroomId getId() {
    return id;
  }

  @Override
  protected boolean sameIdentityAs(final Storeroom other) {
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
    Storeroom storeroom = (Storeroom) o;
    return Objects.equals(id, storeroom.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public static final class Builder {
    private StoreroomName name;
    private StoreroomId id;

    private Builder() {
    }

    public Builder withName(StoreroomName val) {
      this.name = val;
      return this;
    }

    public Builder withId(StoreroomId val) {
      this.id = val;
      return this;
    }

    public Storeroom build() {
      return new Storeroom(this);
    }

  }
}