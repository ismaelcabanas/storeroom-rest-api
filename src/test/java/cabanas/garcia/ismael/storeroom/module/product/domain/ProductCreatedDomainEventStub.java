package cabanas.garcia.ismael.storeroom.module.product.domain;

public final class ProductCreatedDomainEventStub {

  private ProductCreatedDomainEventStub() {
  }

  public static ProductCreatedDomainEvent create(ProductId productId, ProductName productName) {
    return ProductCreatedDomainEvent.builder()
            .withId(productId.getId())
            .withName(productName.getName())
            .build();
  }
}
