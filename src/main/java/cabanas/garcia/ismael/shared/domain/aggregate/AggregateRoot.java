package cabanas.garcia.ismael.shared.domain.aggregate;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AggregateRoot {

  private List<DomainEvent> domainEvents = new ArrayList<>();

  public List<DomainEvent> pullDomainEvents() {
    List<DomainEvent> domainEventsToPull = this.domainEvents.stream().collect(Collectors.toList());
    this.domainEvents = new ArrayList<>();
    return domainEventsToPull;
  }

  protected void record(DomainEvent domainEvent) {
    domainEvents.add(domainEvent);
  }
}
