package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.product.application.NewProductApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  public NewProductApplicationService newProductApplicationService() {
    return new NewProductApplicationService();
  }
}
