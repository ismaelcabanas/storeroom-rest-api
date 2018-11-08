package cabanas.garcia.ismael.storeroom.module.product.application.create;

import cabanas.garcia.ismael.storeroom.module.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductName;

public final class ProductCreateCommandStub {

  private ProductCreateCommandStub() {
  }

  public static ProductCreateCommand create(ProductId productId, ProductName productName) {
    return ProductCreateCommand.builder()
            .withId(productId.getId())
            .withName(productName.getName())
            .build();
  }
}
