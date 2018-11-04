package cabanas.garcia.ismael.storeroom.module.storeroom.application.addProduct;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;

public final class AddProductCommandStub {

  private AddProductCommandStub() {
  }

  public static AddProductCommand create(StoreroomId storeroomId, ProductId productId, ProductName productName) {
    return AddProductCommand.builder()
            .withId(productId.getValue())
            .withName(productName.getName())
            .withStoreroomId(storeroomId.getValue())
            .build();
  }
}
