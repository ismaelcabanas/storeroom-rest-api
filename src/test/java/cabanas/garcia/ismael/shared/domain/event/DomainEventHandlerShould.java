package cabanas.garcia.ismael.shared.domain.event;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class DomainEventHandlerShould {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Spy
  private DomainEventSubscriber<TestableDomainEvent> domainEventSubscriber = new TestableDomainEventSubscriber();

  @Test
  public void be_singleton() {
    assertThat(DomainEventHandler.class.getDeclaredConstructors()).allMatch(constructor -> !constructor.isAccessible());
    assertThat(DomainEventHandler.publisher()).isSameAs(DomainEventHandler.publisher());
    assertThat(DomainEventHandler.subscription()).isSameAs(DomainEventHandler.subscription());
    assertThat(DomainEventHandler.publisher()).isSameAs(DomainEventHandler.subscription());
  }

  @Test
  public void publish_event_to_subscribers_subscribed_to_that_event() {
    DomainEventHandler.subscription().subscribe(domainEventSubscriber);
    DomainEventHandler.publisher().publish(new TestableDomainEvent());
    DomainEventHandler.publisher().publish(new AnotherTestableDomainEvent());

    verify(domainEventSubscriber, times(1)).handle(any(TestableDomainEvent.class));
  }

  private static class TestableDomainEventSubscriber implements DomainEventSubscriber<TestableDomainEvent> {
    @Override
    public boolean isSubscribedTo(DomainEvent domainEvent) {
      return domainEvent instanceof TestableDomainEvent;
    }

    @Override
    public void handle(TestableDomainEvent domainEvent) {
    }
  }

  private static class TestableDomainEvent extends DomainEvent {

  }

  private static class AnotherTestableDomainEvent extends DomainEvent {

  }
}
