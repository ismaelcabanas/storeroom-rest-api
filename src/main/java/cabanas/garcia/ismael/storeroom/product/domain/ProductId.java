package cabanas.garcia.ismael.storeroom.product.domain;

import cabanas.garcia.ismael.shared.domain.Id;

public final class ProductId extends Id<ProductId, String> {

  public ProductId(String id) {
    super(id);
  }
}