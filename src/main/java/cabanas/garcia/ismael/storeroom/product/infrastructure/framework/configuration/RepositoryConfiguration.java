package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.PostgresProductRepository;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.ProductCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
  @Bean
  public ProductRepository productRepository(ProductCrudRepository productCrudRepository) {
    return new PostgresProductRepository(productCrudRepository);
  }
}
