package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.product.application.NewProductApplicationService;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfiguration {
  @Bean
  public NewProductApplicationService newProductApplicationService(ProductRepository productRepository) {
    return new NewProductApplicationService(productRepository);
  }
}
