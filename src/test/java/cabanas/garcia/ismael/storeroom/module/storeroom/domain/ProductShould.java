package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductShould {

  private static final Quantity QUANTITY_TWO = Quantity.builder().withValue(2).build();
  private static final Stock STOCK_OF_FOUR = Stock.builder().withValue(4).build();

  @Test
  public void have_zero_stock_when_product_is_created() {
    // when
    Product product = Product.builder()
            .withId(ProductIdStub.random())
            .withName(ProductNameStub.random())
            .build();

    // then
    assertThat(product.stock()).isEqualTo(Stock.builder().withValue(0).build());
  }

  @Test
  public void add_new_stock_to_product_with_zero_stock() {
    // given
    Product product = Product.builder()
            .withId(ProductIdStub.random())
            .withName(ProductNameStub.random())
            .build();

    // when
    product.addStock(QUANTITY_TWO);

    // then
    assertThat(product.stock()).isEqualTo(Stock.builder().withValue(2).build());
  }

  @Test
  public void add_new_stock_to_product_with_given_stock() {
    // given
    Product product = Product.builder()
            .withId(ProductIdStub.random())
            .withName(ProductNameStub.random())
            .build();
    product.addStock(QUANTITY_TWO);

    // when
    product.addStock(QUANTITY_TWO);

    // then
    assertThat(product.stock()).isEqualTo(STOCK_OF_FOUR);
  }
}
