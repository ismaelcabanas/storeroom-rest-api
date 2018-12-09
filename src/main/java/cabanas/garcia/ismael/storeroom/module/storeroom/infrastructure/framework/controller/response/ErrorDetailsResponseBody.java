package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Objects;

@JsonDeserialize(builder = ErrorDetailsResponseBody.Builder.class)
public class ErrorDetailsResponseBody {
  private final String message;
  private final String details;

  private ErrorDetailsResponseBody(Builder builder) {
    this.message = builder.message;
    this.details = builder.details;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDetailsResponseBody that = (ErrorDetailsResponseBody) o;
    return Objects.equals(message, that.message)
            && Objects.equals(details, that.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(message, details);
  }

  public static Builder builder() {
    return new Builder();
  }


  public static final class Builder {
    private String message;
    private String details;

    private Builder() {
    }

    public Builder withMessage(String val) {
      this.message = val;
      return this;
    }

    public Builder withDetails(String val) {
      this.details = val;
      return this;
    }

    public ErrorDetailsResponseBody build() {
      return new ErrorDetailsResponseBody(this);
    }
  }
}
