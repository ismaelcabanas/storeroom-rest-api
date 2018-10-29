package cabanas.garcia.ismael.storeroom.module.storeroom.application.create;

public final class StoreroomCreateCommand {
  private final String id;
  private final String name;

  private StoreroomCreateCommand(Builder builder) {
    this.id = builder.id;
    this.name = builder.name;
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

    public StoreroomCreateCommand build() {
      return new StoreroomCreateCommand(this);
    }
  }
}
