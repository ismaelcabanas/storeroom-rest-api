package cabanas.garcia.ismael.storeroom.product.application.newproduct;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;

public class NewProduct {
  private final ProductRepository productRepository;

  public NewProduct(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product execute(ProductId productId, ProductName productName) {
    return productRepository.save(Product.product()
            .withName(productName)
            .withId(productId)
            .build());
  }
}
