package cabanas.garcia.ismael.storeroom.module.product.create;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.product.domain.Product;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductCreatedDomainEventStub;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

public class ProductCreatorShould {

  @Mock
  private ProductRepository productRepository;

  @Mock
  private DomainEventPublisher domainEventPublisher;

  private ProductCreator productCreator;

  @Before
  public void setUp() {
    // With this call to initMocks we tell Mockito to process the annotations
    MockitoAnnotations.initMocks(this);
    productCreator = new ProductCreator(productRepository, domainEventPublisher);
  }

  @Test
  public void create_product() {
    // given
    ProductId productId = ProductIdStub.random();
    ProductName productName = ProductNameStub.random();
    Product product = ProductStub.create(productId, productName);
    DomainEvent domainEvent = ProductCreatedDomainEventStub.create(productId, productName);
    ProductCreateCommand command = ProductCreateCommandStub.create(productId, productName);

    // when
    productCreator.execute(command);

    //then
    shouldSaveProduct(product);
    shouldPublishEventDomains(domainEvent);
  }

  private void shouldPublishEventDomains(DomainEvent domainEvent) {
    verify(domainEventPublisher).publish(Arrays.asList(domainEvent));
  }

  private void shouldSaveProduct(Product product) {
    verify(productRepository).save(product);
  }

}
