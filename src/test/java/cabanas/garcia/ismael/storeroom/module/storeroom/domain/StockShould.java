package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class StockShould {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Stock.class).verify();
  }
}
