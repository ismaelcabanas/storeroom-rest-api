package cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.product.application.create.ProductCreator;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApplicationServiceConfiguration {

  @Bean
  public ProductCreator productCreator(ProductRepository productRepository,
                                       DomainEventPublisher domainEventPublisher) {
    return new ProductCreator(productRepository, domainEventPublisher);
  }
}
