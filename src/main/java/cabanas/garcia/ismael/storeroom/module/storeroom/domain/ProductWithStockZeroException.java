package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public class ProductWithStockZeroException extends RuntimeException {

  public ProductWithStockZeroException(String message) {
    super(message);
  }
}
