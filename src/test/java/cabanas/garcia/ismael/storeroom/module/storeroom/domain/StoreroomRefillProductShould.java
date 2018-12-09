package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.QuantityStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StoreroomRefillProductShould {
  private static final Quantity QUANTITY_THREE = Quantity.builder().withValue(3).build();

  @Test
  public void raise_event_when_product_refilled() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());
    storeroom.addProduct(product);

    // when
    storeroom.reFill(product.id(), QUANTITY_THREE);

    // then
    assertThat(storeroom.pullDomainEvents())
            .contains(ProductRefilledDomainEvent.builder()
                    .withProductId(product.id().getValue())
                    .withQuantity(QUANTITY_THREE.getValue())
                    .build());
  }

  @Test
  public void update_stock_when_refill_product_in_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = ProductStub.random();
    storeroom.addProduct(product);

    // when
    Product productRefilled = storeroom.reFill(product.id(), QUANTITY_THREE);

    // then
    assertThat(productRefilled.stock()).isEqualTo(Stock.builder().withValue(3).build());
  }

  @Test
  public void throw_exception_when_refill_not_existent_product_in_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product nonExistentProduct = ProductStub.random();

    // when
    Throwable thrown = catchThrowable(() -> storeroom.reFill(nonExistentProduct.id(), QuantityStub.random()));

    // then
    assertThat(thrown).isInstanceOf(ProductNotInStoreroomException.class);
  }

}
