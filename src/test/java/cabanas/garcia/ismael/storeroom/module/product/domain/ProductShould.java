package cabanas.garcia.ismael.storeroom.module.product.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class ProductShould {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Product.class).withOnlyTheseFields("productId").verify();
  }
}
