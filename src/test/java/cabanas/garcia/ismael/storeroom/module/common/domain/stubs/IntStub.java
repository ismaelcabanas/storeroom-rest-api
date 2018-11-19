package cabanas.garcia.ismael.storeroom.module.common.domain.stubs;

import org.apache.commons.lang.math.RandomUtils;

public final class IntStub {

  private IntStub() {
  }

  public static int randomPositive() {
    return RandomUtils.nextInt();
  }
}
