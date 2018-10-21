package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.application.create.StoreroomCreator;
import cabanas.garcia.ismael.storeroom.domain.StoreroomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreroomApplicationServiceConfiguration {

  @Bean
  public StoreroomCreator storeroomCreator(StoreroomRepository storeroomRepository) {
    return new StoreroomCreator(storeroomRepository);
  }
}
