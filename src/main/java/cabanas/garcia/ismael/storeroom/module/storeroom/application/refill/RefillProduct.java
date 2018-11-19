package cabanas.garcia.ismael.storeroom.module.storeroom.application.refill;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

import java.util.Optional;

public class RefillProduct {
  private final StoreroomRepository storeroomRepository;
  private final DomainEventPublisher domainEventPublisher;

  public RefillProduct(StoreroomRepository storeroomRepository, DomainEventPublisher domainEventPublisher) {
    this.storeroomRepository = storeroomRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public void execute(RefillProductCommand command) {
    Optional<Storeroom> storeroom = storeroomRepository.findById(new StoreroomId(command.getStoreroomId()));

    storeroom.ifPresent(st -> {
      st.reFill(
              new ProductId(command.getProductId()),
              Quantity.builder().withValue(command.getQuantity()).build());
      storeroomRepository.update(st);
      domainEventPublisher.publish(st.pullDomainEvents());
    });
  }
}
