package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.storeroom.product.domain.ProductId;

public class ProductNotExistException extends RuntimeException {
  public ProductNotExistException(ProductId productId) {
    super("The product " + productId.getValue() + " doesn't exist.");
  }
}
