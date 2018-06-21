package cabanas.garcia.ismael.storeroom.product.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductNameShould {

  private static final String SOME_NAME = "some_name";

  @Test
  public void buildWithCorrectInformation() {

    // when
    ProductName actual = ProductName.productName().withName(SOME_NAME).build();

    // then
    assertThat(actual).isNotNull();
    assertThat(actual.getName()).isEqualTo(SOME_NAME);
  }
}
