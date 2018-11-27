package cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct;


import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddProduct {
  private static final Logger LOGGER = LoggerFactory.getLogger(AddProduct.class.getName());

  private final StoreroomRepository repository;
  private final DomainEventPublisher domainEventPublisher;

  public AddProduct(StoreroomRepository repository, DomainEventPublisher domainEventPublisher) {
    this.repository = repository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public Product execute(AddProductCommand command) {
    Storeroom storeroom = repository
            .findById(new StoreroomId(command.getStoreroomId()))
            .orElseThrow(StoreroomNotFoundException::new);

    LOGGER.debug("Storeroom {} founded", storeroom);

    Product productAdded = storeroom.addProduct(Product.builder()
            .withId(new ProductId(command.getId()))
            .withName(new ProductName(command.getName()))
            .build());

    repository.update(storeroom);

    domainEventPublisher.publish(storeroom.pullDomainEvents());

    return productAdded;
  }
}
