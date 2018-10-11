package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.aggregate.AggregateRoot;
import com.google.common.base.Preconditions;

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
  protected StoreroomId getId() {
    return id;
  }

  @Override
  protected boolean sameIdentityAs(Storeroom other) {
    if (other != null) {
      if (other.id == null && id == null) {
        return true;
      } else if (id != null) {
        return id.sameValueAs(other.id);
      }
    }

    return false;
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
