package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import org.apache.commons.lang3.RandomStringUtils;

public final class StoreroomNameStub {
  private StoreroomNameStub() {
  }

  public static StoreroomName random() {
    return new StoreroomName(RandomStringUtils.random(25));
  }
}