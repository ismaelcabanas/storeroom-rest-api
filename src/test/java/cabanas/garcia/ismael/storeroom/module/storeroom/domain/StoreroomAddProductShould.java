package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
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
                    .withId(product.id().getValue())
                    .withName(product.getName().getName())
                    .withStoreroomId(storeroom.id().getValue())
                    .build());
  }

  @Test
  public void contains_added_product() {
    // given
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.contains(product)).isTrue();
  }
}
