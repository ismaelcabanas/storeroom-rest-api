package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.consume.ConsumeProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreator;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct.AddProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.refill.RefillProduct;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreroomApplicationServiceConfiguration {

  @Bean
  public StoreroomCreator storeroomCreator(StoreroomRepository storeroomRepository) {
    return new StoreroomCreator(storeroomRepository);
  }

  @Bean(name = "storeroomProductCreator")
  public AddProduct productCreator(
          StoreroomRepository storeroomRepository,
          DomainEventPublisher domainEventPublisher) {
    return new AddProduct(storeroomRepository, domainEventPublisher);
  }

  @Bean
  public RefillProduct refillProduct(StoreroomRepository storeroomRepository,
                                     DomainEventPublisher domainEventPublisher) {
    return new RefillProduct(storeroomRepository, domainEventPublisher);
  }

  @Bean
  public ConsumeProduct consumeProduct(StoreroomRepository storeroomRepository,
                                       DomainEventPublisher domainEventPublisher) {
    return new ConsumeProduct(storeroomRepository, domainEventPublisher);
  }
}
