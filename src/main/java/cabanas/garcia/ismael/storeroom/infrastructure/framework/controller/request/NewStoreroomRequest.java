package cabanas.garcia.ismael.storeroom.infrastructure.framework.controller.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = NewStoreroomRequest.Builder.class)
public class NewStoreroomRequest {
  private final String id;
  private final String name;

  private NewStoreroomRequest(Builder builder) {
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

    public NewStoreroomRequest build() {
      return new NewStoreroomRequest(this);
    }
  }
}
