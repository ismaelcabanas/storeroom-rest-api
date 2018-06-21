package cabanas.garcia.ismael.storeroom.product.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductShould {

  private static final String SOME_ID = "some_id";
  private static final String SOME_NAME = "some_name";

  @Test
  public void buildProduct() {
    // given
    ProductId productId = ProductId.productId().withId(SOME_ID).build();
    ProductName productName = ProductName.productName().withName(SOME_NAME).build();

    // when
    Product actual = Product.product().withId(productId).withName(productName).build();

    // then
    assertThat(actual).isNotNull();
    assertThat(actual.getProductId()).isEqualTo(ProductId.productId().withId(SOME_ID).build());
    assertThat(actual.getProductName()).isEqualTo(ProductName.productName().withName(SOME_NAME).build());
  }
}
