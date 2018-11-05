package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductAddedDomainEvent;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;

public final class ProductCreatedDomainEventStub {

  private ProductCreatedDomainEventStub() {
  }

  public static ProductAddedDomainEvent create(StoreroomId storeroomId, ProductId productId, ProductName productName) {
    return ProductAddedDomainEvent.builder()
            .withId(productId.getValue())
            .withName(productName.getName())
            .withStoreroomId(storeroomId.getValue())
            .build();
  }
}
