package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
  public void return_stock_5_when_add_3_to_stock_with_2() {
    // given
    Stock stock = Stock.builder().withValue(2).build();

    // when
    Stock actual = stock.add(Quantity.builder().withValue(3).build());

    // then
    assertThat(actual).isEqualTo(Stock.builder().withValue(5).build());
  }

  @Test
  public void return_stock_3_when_decrease_1_from_stock_with_4() {
    // given
    Stock stock = Stock.builder().withValue(4).build();

    // when
    Stock actual = stock.decrease(Quantity.builder().withValue(1).build());

    // then
    assertThat(actual).isEqualTo(Stock.builder().withValue(3).build());
  }

  @Test
  public void throw_stock_overflow_exception_when_decrease_quantity_greater_than_current_stock() {
    // given
    int currentStock = 4;
    Stock stock = Stock.builder().withValue(currentStock).build();

    // when
    Throwable thrown = catchThrowable(() -> stock.decrease(Quantity.builder().withValue(currentStock + 1).build()));

    // then
    assertThat(thrown).isInstanceOf(StockOverflowException.class);
    assertThat(thrown.getMessage()).isEqualTo("There is not enough stock to consume.");
  }
}
