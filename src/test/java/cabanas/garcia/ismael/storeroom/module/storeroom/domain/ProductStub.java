package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public class ProductStub {
  private ProductStub() {
  }

  public static Product create(ProductId productId, ProductName productName) {
    return Product.builder()
            .withId(productId)
            .withName(productName)
            .build();
  }
}
