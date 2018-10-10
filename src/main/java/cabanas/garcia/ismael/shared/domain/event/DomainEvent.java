package cabanas.garcia.ismael.shared.domain.event;

import java.time.OffsetDateTime;

public abstract class DomainEvent {
  private OffsetDateTime occurredAt = OffsetDateTime.now();

  public OffsetDateTime getOccurredAt() {
    return occurredAt;
  }
}
