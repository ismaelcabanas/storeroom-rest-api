package cabanas.garcia.ismael.storeroom.module.storeroom.application.addProduct;


import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

public class AddProduct {
  private final StoreroomRepository repository;
  private final DomainEventPublisher domainEventPublisher;

  public AddProduct(StoreroomRepository repository, DomainEventPublisher domainEventPublisher) {
    this.repository = repository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public void execute(AddProductCommand command) {
    Product product = Product.create(
            new StoreroomId(command.getStoreroomId()),
            new ProductId(command.getId()),
            new ProductName(command.getName())
    );

    repository.save(product);

    domainEventPublisher.publish(product.pullDomainEvents());
  }
}
