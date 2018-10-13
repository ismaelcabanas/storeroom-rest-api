package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.storeroom.product.domain.ProductId;

public class ProductAlreadyExistInStoreroomException extends RuntimeException {
  public ProductAlreadyExistInStoreroomException(ProductId productId) {
    super("The product " + productId.getValue() + " already exist.");
  }
}
