package cabanas.garcia.ismael.storeroom.product.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductIdShould {
  private static final String SOME_ID = "some_id";

  @Test
  public void buildWithCorrectInformation() {

    // when
    ProductId actual = ProductId.productId().withId(SOME_ID).build();

    // then
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isEqualTo(SOME_ID);
  }
}
