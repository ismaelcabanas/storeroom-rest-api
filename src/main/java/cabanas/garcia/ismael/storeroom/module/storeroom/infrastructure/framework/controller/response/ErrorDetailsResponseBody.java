package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.controller.response;

import java.util.Objects;

public class ErrorDetailsResponseBody {
  private final String message;
  private final String details;

  public ErrorDetailsResponseBody(String message, String details) {
    this.message = message;
    this.details = details;
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
}
