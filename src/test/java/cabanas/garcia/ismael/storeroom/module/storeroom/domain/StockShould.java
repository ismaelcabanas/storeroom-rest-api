package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.QuantityStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StockStub;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StockShould {

  @Test
  public void be_a_value_object() {
    // when
    Stock stock = Stock.builder().withValue(5).build();

    // then
    assertThat(stock).isInstanceOf(ValueObject.class);

  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Stock.class).verify();
  }

  @Test
  public void add_quantity() {
    // given
    Stock stock = StockStub.random();
    Quantity quantity = QuantityStub.random();
    Stock expectedStock = Stock.builder().withValue(stock.getValue() + quantity.getValue()).build();

    // when
    Stock actual = stock.add(quantity);

    // then
    assertThat(actual).isEqualTo(expectedStock);
  }
}
