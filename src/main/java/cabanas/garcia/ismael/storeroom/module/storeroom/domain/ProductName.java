package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;

public class ProductName extends ValueObject<ProductName> {
  private final String name;

  public ProductName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  protected boolean sameValueAs(ProductName other) {
    return false;
  }
}
