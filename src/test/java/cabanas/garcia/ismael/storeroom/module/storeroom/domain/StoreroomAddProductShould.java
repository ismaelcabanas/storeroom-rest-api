package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreroomAddProductShould {

  @Test
  public void register_product_added_event() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.pullDomainEvents())
            .contains(ProductAddedDomainEvent.builder()
                    .withId(product.id().getValue())
                    .withName(product.name().getName())
                    .withStoreroomId(storeroom.id().getValue())
                    .build());
  }

  @Test
  public void contains_added_product() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.contains(product)).isTrue();
  }

  @Test
  public void add_product_with_stock_zero_if_product_does_not_exist_in_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = ProductStub.random();

    // when
    Product productAdded = storeroom.addProduct(product);

    // then
    assertThat(productAdded.stock()).isEqualTo(Stock.builder().withValue(0).build());
  }

}
