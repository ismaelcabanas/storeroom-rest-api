package cabanas.garcia.ismael.storeroom.domain;

public final class StoreroomStub {

  private StoreroomStub() {
  }

  public static Storeroom create(StoreroomId storeroomId, StoreroomName storeroomName) {
    return Storeroom.builder()
            .withId(storeroomId)
            .withName(storeroomName)
            .build();
  }
}
