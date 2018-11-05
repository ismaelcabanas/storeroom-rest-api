package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;

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
