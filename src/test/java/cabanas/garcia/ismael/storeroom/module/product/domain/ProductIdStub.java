package cabanas.garcia.ismael.storeroom.module.product.domain;

import java.util.UUID;

public final class ProductIdStub {

  private ProductIdStub() {
  }
  public static ProductId random() {
    return ProductId.productId().withId(UUID.randomUUID().toString()).build();
  }
}