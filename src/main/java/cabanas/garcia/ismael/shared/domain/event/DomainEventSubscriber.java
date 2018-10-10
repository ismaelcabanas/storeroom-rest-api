package cabanas.garcia.ismael.shared.domain.event;

public interface DomainEventSubscriber<T extends DomainEvent> {
  boolean isSubscribedTo(DomainEvent domainEvent);

  void handle(T domainEvent);
}