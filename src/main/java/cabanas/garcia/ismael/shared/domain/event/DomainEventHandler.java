package cabanas.garcia.ismael.shared.domain.event;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DomainEventHandler implements DomainEventPublisher, DomainEventSubscription {
  private static final DomainEventHandler INSTANCE = new DomainEventHandler();
  private Set<DomainEventSubscriber> subscribers = new HashSet<>();

  @Override
  public <T extends DomainEvent> DomainEventPublisher publish(Collection<T> events) {
    subscribers.forEach(
        subscriber -> events.stream()
        .filter(subscriber::isSubscribedTo)
        .forEach(subscriber::handle));
    return this;
  }

  @Override
  public <T extends DomainEvent> DomainEventPublisher publish(T event) {
    return publish(Arrays.asList(event));
  }

  private DomainEventHandler() {
  }

  public static DomainEventPublisher publisher() {
    return INSTANCE;
  }

  public static DomainEventSubscription subscription() {
    return INSTANCE;
  }

  @Override
  public DomainEventSubscription subscribe(DomainEventSubscriber<?> subscriber) {
    subscribers.add(subscriber);
    return this;
  }

  @Override
  public DomainEventSubscription unsubscribe(DomainEventSubscriber<?> subscriber) {
    subscribers.remove(subscriber);
    return this;
  }
}
