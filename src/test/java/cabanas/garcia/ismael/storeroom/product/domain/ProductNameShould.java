package cabanas.garcia.ismael.storeroom.product.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class ProductNameShould {

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(ProductName.class).verify();
  }

}
