package cabanas.garcia.ismael.shared.domain.event;

public interface DomainEventSubscription {
  DomainEventSubscription subscribe(DomainEventSubscriber<?> subscriber);

  DomainEventSubscription unsubscribe(DomainEventSubscriber<?> subscriber);
}
