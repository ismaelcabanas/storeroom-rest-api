package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.shared.domain.event.DomainEventHandler;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.product.application.create.ProductCreator;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApplicationServiceConfiguration {

  @Bean
  public DomainEventPublisher domainEventPublisher() {
    return DomainEventHandler.publisher();
  }

  @Bean
  public ProductCreator productCreator(ProductRepository productRepository,
                                       DomainEventPublisher domainEventPublisher) {
    return new ProductCreator(productRepository, domainEventPublisher);
  }
}
