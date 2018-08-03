package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.product.application.newproduct.NewProduct;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfiguration {
  @Bean
  public NewProduct newProductApplicationService(ProductRepository productRepository) {
    return new NewProduct(productRepository);
  }
}
