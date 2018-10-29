package cabanas.garcia.ismael.storeroom.module.product.application.create;

import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.product.domain.Product;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductRepository;

public class ProductCreator {
  private final ProductRepository productRepository;
  private final DomainEventPublisher domainEventPublisher;

  public ProductCreator(ProductRepository productRepository, DomainEventPublisher domainEventPublisher) {
    this.productRepository = productRepository;
    this.domainEventPublisher = domainEventPublisher;
  }

  public void execute(ProductCreateCommand command) {
    Product product = Product.create(
            ProductId.productId().withId(command.getId()).build(),
            ProductName.productName().withName(command.getName()).build()
    );

    productRepository.save(product);

    domainEventPublisher.publish(product.pullDomainEvents());
  }
}
