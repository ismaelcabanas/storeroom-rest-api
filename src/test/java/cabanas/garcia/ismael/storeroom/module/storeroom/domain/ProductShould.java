package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductShould {

  @Test
  public void have_zero_stock_when_product_is_created() {
    // when
    Product product = Product.builder()
            .withId(ProductIdStub.random())
            .withName(ProductNameStub.random())
            .build();

    // then
    assertThat(product.stock() == Stock.ZERO);
  }
}
