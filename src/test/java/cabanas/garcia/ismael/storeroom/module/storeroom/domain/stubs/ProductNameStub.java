package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import org.apache.commons.lang3.RandomStringUtils;

public final class ProductNameStub {
  private ProductNameStub() {
  }

  public static ProductName random() {
    return new ProductName(RandomStringUtils.random(50));
  }
}
