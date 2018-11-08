package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.module.storeroom.application.create.StoreroomCreator;
import cabanas.garcia.ismael.storeroom.module.storeroom.application.createProduct.ProductCreator;
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
  public ProductCreator productCreator() {
    return new ProductCreator();
  }
}
