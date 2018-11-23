package cabanas.garcia.ismael.storeroom.module.storeroom.application.refill;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

public class RefillProduct {
  private final StoreroomRepository repository;
  private final DomainEventPublisher domainEventPublisher;

  public RefillProduct(StoreroomRepository storeroomRepository, DomainEventPublisher domainEventPublisher) {
    this.repository = storeroomRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public void execute(RefillProductCommand command) {
    StoreroomId storeroomId = new StoreroomId(command.getStoreroomId());
    Storeroom storeroom = repository
            .findById(storeroomId)
            .orElseThrow(() -> new StoreroomNotFoundException(storeroomId));

    storeroom.reFill(
            new ProductId(command.getProductId()),
            Quantity.builder().withValue(command.getQuantity()).build()
    );
    repository.update(storeroom);
    domainEventPublisher.publish(storeroom.pullDomainEvents());
  }
}
