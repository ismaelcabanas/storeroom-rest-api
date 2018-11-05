package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class StoreroomShould {
  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Storeroom.class).withOnlyTheseFields("id").verify();
  }
}
