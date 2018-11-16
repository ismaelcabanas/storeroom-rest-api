package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StoreroomAddProductShould {
  private static final Stock STOCK_ONE = Stock.builder().withValue(1).build();

  @Test
  public void register_product_added_event() {
    // given
    Storeroom storeroom = StoreroomStub.random();
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random(), STOCK_ONE);

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
    Storeroom storeroom = StoreroomStub.random();
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random(), STOCK_ONE);

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.contains(product)).isTrue();
  }

  @Test
  public void add_product_with_stock_greater_than_zero() {
    // given
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.createWithStockZero();

    // when
    Throwable thrown = catchThrowable(() -> storeroom.addProduct(product));

    // then
    assertThat(thrown).isInstanceOf(ProductWithStockZeroException.class);
  }
}
