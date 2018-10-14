package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.storeroom.product.domain.ProductId;

public class ProductAlreadyExistException extends RuntimeException {
  public ProductAlreadyExistException(ProductId productId) {
    super("The product " + productId.getValue() + " already exist.");
  }
}
