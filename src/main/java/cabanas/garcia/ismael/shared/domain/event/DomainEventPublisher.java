package cabanas.garcia.ismael.shared.domain.event;

import java.util.List;

public interface DomainEventPublisher {
  <T extends DomainEvent> DomainEventPublisher publish(List<T> events);

  <T extends DomainEvent> DomainEventPublisher publish(T event);
}
