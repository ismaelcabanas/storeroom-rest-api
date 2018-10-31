package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.UUID;

public final class ProductIdStub {
  private ProductIdStub() {
  }

  public static ProductId random() {
    return new ProductId(UUID.randomUUID().toString());
  }
}
