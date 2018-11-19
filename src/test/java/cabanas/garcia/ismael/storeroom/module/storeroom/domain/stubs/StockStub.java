package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.common.domain.stubs.IntStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Stock;

public final class StockStub {
  private StockStub() {
  }

  public static Stock random() {
    return Stock.builder().withValue(IntStub.randomPositive()).build();
  }
}
