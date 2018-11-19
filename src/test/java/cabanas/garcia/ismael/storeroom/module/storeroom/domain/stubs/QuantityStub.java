package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import org.apache.commons.lang.math.RandomUtils;

public final class QuantityStub {
  public static Quantity random() {
    return Quantity.builder().withValue(RandomUtils.nextInt()).build();
  }
}
