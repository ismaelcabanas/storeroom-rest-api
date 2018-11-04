package cabanas.garcia.ismael.storeroom.module.storeroom.application.addProduct;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;

public final class ProductCreateCommandStub {

  private ProductCreateCommandStub() {
  }

  public static ProductCreateCommand create(StoreroomId storeroomId, ProductId productId, ProductName productName) {
    return ProductCreateCommand.builder()
            .withId(productId.getValue())
            .withName(productName.getName())
            .withStoreroomId(storeroomId.getValue())
            .build();
  }
}
