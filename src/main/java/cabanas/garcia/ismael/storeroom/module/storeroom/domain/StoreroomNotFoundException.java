package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public class StoreroomNotFoundException extends RuntimeException {
  private static final String MESSAGE = "Storeroom %s not founded";

  public StoreroomNotFoundException(StoreroomId storeroomId) {
    super(String.format(MESSAGE, storeroomId.getValue()));
  }
}
