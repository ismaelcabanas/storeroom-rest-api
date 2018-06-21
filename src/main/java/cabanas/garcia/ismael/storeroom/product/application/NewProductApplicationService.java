package cabanas.garcia.ismael.storeroom.product.application;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;

import java.util.UUID;

public class NewProductApplicationService {
  public Product execute(ProductName productName) {
    return Product.product()
            .withName(productName)
            .withId(ProductId.productId().withId(UUID.randomUUID().toString()).build())
            .build();
  }
}
