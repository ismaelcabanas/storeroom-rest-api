package cabanas.garcia.ismael.storeroom.application.create;

public final class StoreroomCreateCommandStub {

  private StoreroomCreateCommandStub() {
  }

  public static StoreroomCreateCommand create(String storeroomId, String storeroomName) {
    return StoreroomCreateCommand.builder()
            .withId(storeroomId)
            .withName(storeroomName)
            .build();
  }
}
