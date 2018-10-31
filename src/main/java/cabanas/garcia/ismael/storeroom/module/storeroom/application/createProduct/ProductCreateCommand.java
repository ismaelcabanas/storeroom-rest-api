package cabanas.garcia.ismael.storeroom.module.storeroom.application.createProduct;

public final class ProductCreateCommand {
  private final String id;
  private final String name;
  private final String storeroomId;

  private ProductCreateCommand(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.storeroomId = builder.storeroomId;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public String getStoreroomId() {
    return storeroomId;
  }

  public static final class Builder {
    private String id;
    private String name;
    private String storeroomId;

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

    public Builder withStoreroomId(String val) {
      this.storeroomId = val;
      return this;
    }

    public ProductCreateCommand build() {
      return new ProductCreateCommand(this);
    }

  }
}
