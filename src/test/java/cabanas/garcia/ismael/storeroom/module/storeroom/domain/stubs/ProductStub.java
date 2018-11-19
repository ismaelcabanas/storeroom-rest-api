package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;

public class ProductStub {
  private ProductStub() {
  }

  public static Product create(ProductId productId, ProductName productName) {
    return Product.builder()
            .withId(productId)
            .withName(productName)
            .build();
  }

  public static Product random() {
    return Product.builder()
            .withId(ProductIdStub.random())
            .withName(ProductNameStub.random())
            .build();
  }

}
