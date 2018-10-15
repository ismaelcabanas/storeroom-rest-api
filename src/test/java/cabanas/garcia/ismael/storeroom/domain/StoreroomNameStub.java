package cabanas.garcia.ismael.storeroom.domain;

import org.apache.commons.lang3.RandomStringUtils;

public final class StoreroomNameStub {
  private StoreroomNameStub() {
  }

  public static StoreroomName random() {
    return new StoreroomName(RandomStringUtils.random(25));
  }
}