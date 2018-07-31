package cabanas.garcia.ismael.storeroom.product.domain;

public final class ProductStub {
  private ProductStub() {

  }

  public static Product create(ProductId productId, ProductName productName) {
    return Product.product().withId(productId).withName(productName).build();
  }
}