package cabanas.garcia.ismael.shared.domain.event;

import java.util.Collection;

public interface DomainEventPublisher {
  <T extends DomainEvent> DomainEventPublisher publish(Collection<T> events);

  <T extends DomainEvent> DomainEventPublisher publish(T event);
}
