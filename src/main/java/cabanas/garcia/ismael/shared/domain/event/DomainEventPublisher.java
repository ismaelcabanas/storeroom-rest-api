package cabanas.garcia.ismael.shared.domain.event;

import java.util.List;

public interface DomainEventPublisher {
  void publish(List<DomainEvent> domainEvent);
}
