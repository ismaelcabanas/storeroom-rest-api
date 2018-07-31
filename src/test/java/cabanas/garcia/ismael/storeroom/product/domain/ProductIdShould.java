package cabanas.garcia.ismael.storeroom.product.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class ProductIdShould {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(ProductId.class).verify();
  }
}
