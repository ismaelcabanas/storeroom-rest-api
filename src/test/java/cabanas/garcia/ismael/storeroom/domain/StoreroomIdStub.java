package cabanas.garcia.ismael.storeroom.domain;

import java.util.UUID;

public final class StoreroomIdStub {

  private StoreroomIdStub() {
  }

  public static StoreroomId random() {
    return new StoreroomId(UUID.randomUUID().toString());
  }
}
