package cabanas.garcia.ismael.shared.infrastructure.event;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultEventPublisher implements DomainEventPublisher {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEventPublisher.class);

  @Override
  public void publish(List<DomainEvent> domainEvents) {
    domainEvents
            .forEach(domainEvent -> LOGGER.info("Evento publicado: " + domainEvent));
  }
}
