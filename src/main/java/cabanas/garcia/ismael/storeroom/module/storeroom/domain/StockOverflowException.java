package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public class StockOverflowException extends RuntimeException {
  private static final String MESSAGE = "There is not enough stock to consume.";

  public StockOverflowException() {
    super(MESSAGE);
  }
}
