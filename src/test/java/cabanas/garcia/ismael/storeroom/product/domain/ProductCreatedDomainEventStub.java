package cabanas.garcia.ismael.storeroom.product.domain;

public final class ProductCreatedDomainEventStub {

  private ProductCreatedDomainEventStub() {
  }

  public static ProductCreatedDomainEvent create(ProductId productId, ProductName productName) {
    return ProductCreatedDomainEvent.builder()
            .withId(productId.getValue())
            .withName(productName.getName())
            .build();
  }
}
