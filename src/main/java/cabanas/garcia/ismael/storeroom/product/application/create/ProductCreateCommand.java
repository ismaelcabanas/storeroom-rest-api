package cabanas.garcia.ismael.storeroom.product.application.create;

import java.util.Objects;

public final class ProductCreateCommand {
  private final String id;
  private final String name;

  private ProductCreateCommand(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductCreateCommand that = (ProductCreateCommand) o;
    return Objects.equals(id, that.id)
            && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String id;
    private String name;

    private Builder() {
    }

    public Builder withId(String val) {
      this.id = val;
      return this;
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public ProductCreateCommand build() {
      return new ProductCreateCommand(this);
    }
  }
}
