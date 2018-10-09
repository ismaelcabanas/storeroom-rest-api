package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.shared.infrastructure.event.DefaultEventPublisher;
import cabanas.garcia.ismael.storeroom.product.application.create.ProductCreator;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationServiceConfiguration {

  @Bean
  public DomainEventPublisher domainEventPublisher() {
    return new DefaultEventPublisher();
  }

  @Bean
  public ProductCreator newProductApplicationService(ProductRepository productRepository,
                                                     DomainEventPublisher domainEventPublisher) {
    return new ProductCreator(productRepository, domainEventPublisher);
  }
}
