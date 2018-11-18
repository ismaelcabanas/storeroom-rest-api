package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public class ProductNotInStoreroomException extends RuntimeException {

  private static final String MESSAGE = "Product with identifier %s does not exist in the storeroom";

  public ProductNotInStoreroomException(ProductId productId) {
    super(String.format(MESSAGE, productId.getValue()));
  }
}
