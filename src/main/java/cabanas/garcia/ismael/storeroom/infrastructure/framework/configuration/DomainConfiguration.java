package cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.shared.domain.event.DomainEventHandler;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
  @Bean
  public DomainEventPublisher domainEventPublisher() {
    return DomainEventHandler.publisher();
  }

}
