package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreroomAddProductShould {
  @Test
  public void register_product_added_event() {
    // given
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.pullDomainEvents())
            .contains(ProductAddedDomainEvent.builder()
                    .withId(product.getId().getValue())
                    .withName(product.getName().getName())
                    .withStoreroomId(storeroom.getId().getValue())
                    .build());
  }
}
