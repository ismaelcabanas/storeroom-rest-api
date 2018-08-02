package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;

public class PostgresProductRepository implements ProductRepository {

  private final ProductCrudRepository productCrudRepository;

  public PostgresProductRepository(ProductCrudRepository productCrudRepository) {
    this.productCrudRepository = productCrudRepository;
  }

  @Override
  public Product save(Product product) {
    return null;
  }
}
