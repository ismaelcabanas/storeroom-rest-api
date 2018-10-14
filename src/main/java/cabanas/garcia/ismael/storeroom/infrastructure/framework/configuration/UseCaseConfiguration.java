package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.application.create.StoreroomCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

  @Bean
  public StoreroomCreator storeroomCreator() {
    return new StoreroomCreator();
  }
}
