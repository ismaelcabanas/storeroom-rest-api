package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public final class ProductCreatedDomainEventStub {

  private ProductCreatedDomainEventStub() {
  }

  public static ProductCreatedDomainEvent create(StoreroomId storeroomId, ProductId productId, ProductName productName) {
    return ProductCreatedDomainEvent.builder()
            .withId(productId.getValue())
            .withName(productName.getName())
            .withStoreroomId(storeroomId.getValue())
            .build();
  }
}
