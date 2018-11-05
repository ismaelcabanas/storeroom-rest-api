package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;

import java.util.UUID;

public final class ProductIdStub {
  private ProductIdStub() {
  }

  public static ProductId random() {
    return new ProductId(UUID.randomUUID().toString());
  }
}
