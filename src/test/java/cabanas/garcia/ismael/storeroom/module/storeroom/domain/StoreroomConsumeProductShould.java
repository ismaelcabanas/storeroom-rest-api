package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.QuantityStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StoreroomConsumeProductShould {

  private static final Quantity QUANTITY_THREE = Quantity.builder().withValue(3).build();
  private static final Quantity QUANTITY_ONE = Quantity.builder().withValue(1).build();
  private static final Stock STOCK_OF_TWO = Stock.builder().withValue(2).build();

  @Test
  public void update_stock_when_consume_from_product_in_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = storeroom.addProduct(ProductStub.random());
    storeroom.reFill(product.id(), QUANTITY_THREE);

    // when
    Product productConsumed = storeroom.consume(product.id(), QUANTITY_ONE);

    // then
    assertThat(productConsumed.stock()).isEqualTo(STOCK_OF_TWO);
  }

  @Test
  public void throw_exception_when_consume_from_not_existent_product_in_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product nonExistentProduct = ProductStub.random();

    // when
    Throwable thrown = catchThrowable(() -> storeroom.consume(nonExistentProduct.id(), QuantityStub.random()));

    // then
    assertThat(thrown).isInstanceOf(ProductNotInStoreroomException.class);
  }

  @Test
  public void throw_exception_when_consume_more_quantity_from_product_that_stock_has() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = storeroom.addProduct(ProductStub.random());
    storeroom.reFill(product.id(), QUANTITY_ONE);

    // when
    Throwable thrown = catchThrowable(() -> storeroom.consume(product.id(), QUANTITY_THREE));

    // then
    assertThat(thrown).isInstanceOf(StockOverflowException.class);
  }

  @Test
  public void raise_event_when_product_consumed() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    Product product = storeroom.addProduct(ProductStub.random());
    storeroom.reFill(product.id(), QUANTITY_THREE);

    // when
    storeroom.consume(product.id(), QUANTITY_ONE);

    // then
    assertThat(storeroom.pullDomainEvents())
            .contains(ProductConsumedDomainEvent.builder()
                    .withProductId(product.id().getValue())
                    .withQuantity(QUANTITY_ONE.getValue())
                    .build());
  }
}
