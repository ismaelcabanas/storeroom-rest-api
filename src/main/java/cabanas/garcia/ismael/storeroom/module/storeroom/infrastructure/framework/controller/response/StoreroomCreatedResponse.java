package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = StoreroomCreatedResponse.Builder.class)
public class StoreroomCreatedResponse {
  private final String name;

  private StoreroomCreatedResponse(Builder builder) {
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String name;

    private Builder() {
    }

    public Builder withName(String val) {
      this.name = val;
      return this;
    }

    public StoreroomCreatedResponse build() {
      return new StoreroomCreatedResponse(this);
    }
  }
}
