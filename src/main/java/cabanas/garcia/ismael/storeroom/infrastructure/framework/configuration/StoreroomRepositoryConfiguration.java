package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.domain.StoreroomRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreroomRepositoryConfiguration {

  @Bean
  public StoreroomRepository storeroomRepository() {
    return new StoreroomRepository() {
      @Override
      public void save(Storeroom storeroom) {

      }
    };
  }
}
