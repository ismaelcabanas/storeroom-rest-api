package cabanas.garcia.ismael.storeroom.module.storeroom.application.consume;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

public class ConsumeProduct {
  private final DomainEventPublisher domainEventPublisher;
  private final StoreroomRepository repository;

  public ConsumeProduct(StoreroomRepository storeroomRepository, DomainEventPublisher domainEventPublisher) {
    this.repository = storeroomRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public void execute(ConsumeProductCommand command) {
    StoreroomId storeroomId = new StoreroomId(command.getStoreroomId());
    Storeroom storeroom = repository
            .findById(storeroomId)
            .orElseThrow(() -> new StoreroomNotFoundException(storeroomId));

    storeroom.consume(
            new ProductId(command.getProductId()),
            Quantity.builder().withValue(command.getQuantity()).build()
    );

    repository.update(storeroom);

    domainEventPublisher.publish(storeroom.pullDomainEvents());
  }
}
