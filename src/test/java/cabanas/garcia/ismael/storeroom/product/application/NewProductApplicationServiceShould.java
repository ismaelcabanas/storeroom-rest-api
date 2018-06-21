package cabanas.garcia.ismael.storeroom.product.application;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NewProductApplicationServiceShould {

  private static final String SOME_NAME = "some-name";

  @Test
  public void returnCreatedProduct() {
    // given
    ProductName productName = ProductName.productName().withName(SOME_NAME).build();
    NewProductApplicationService newProductApplicationService =
            new NewProductApplicationService();

    // when
    Product actual = newProductApplicationService.execute(productName);

    // then
    assertThat(actual).isNotNull();
    assertThat(actual.getProductId()).isNotNull();
    assertThat(actual.getProductName()).isEqualTo(productName);
  }
}
