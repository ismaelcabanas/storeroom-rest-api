package cabanas.garcia.ismael.storeroom.module.product.domain;

import org.apache.commons.lang.RandomStringUtils;

public final class ProductNameStub {
  private ProductNameStub() {

  }

  public static ProductName random() {
    return ProductName.productName().withName(RandomStringUtils.random(25)).build();
  }
}