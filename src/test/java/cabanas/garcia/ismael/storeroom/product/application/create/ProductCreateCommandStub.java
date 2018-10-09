package cabanas.garcia.ismael.storeroom.product.application.create;

import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;

public class ProductCreateCommandStub {

  private ProductCreateCommandStub() {
  }

  public static ProductCreateCommand create(ProductId productId, ProductName productName) {
    return ProductCreateCommand.builder()
            .withId(productId.getId())
            .withName(productName.getName())
            .build();
  }
}
