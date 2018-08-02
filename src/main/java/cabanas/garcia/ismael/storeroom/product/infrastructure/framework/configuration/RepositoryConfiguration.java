package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.product.infrastructure.repository.PostgresProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {
  @Bean
  public ProductRepository productRepository() {
    return new PostgresProductRepository();
  }
}
