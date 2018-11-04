package cabanas.garcia.ismael.storeroom.module.storeroom.application.addProduct;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductCreatedDomainEventStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import static org.mockito.Mockito.verify;

public class AddProductShould {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  private AddProduct productCreator;

  @Mock
  private StoreroomRepository storeroomRepository;

  @Mock
  private DomainEventPublisher domainEventPublisher;

  @Before
  public void setUp() {
    productCreator = new AddProduct(storeroomRepository, domainEventPublisher);
  }

  @Test
  public void persist_product_in_storeroom() {
    // given
    StoreroomId storeroomId = StoreroomIdStub.random();
    ProductId productId = ProductIdStub.random();
    ProductName productName = ProductNameStub.random();
    Product product = ProductStub.create(storeroomId, productId, productName);
    AddProductCommand command = AddProductCommandStub.create(storeroomId, productId, productName);

    // when
    productCreator.execute(command);

    //then
    shouldSaveProduct(product);
  }

  @Test
  public void publish_domain_event() {
    // given
    StoreroomId storeroomId = StoreroomIdStub.random();
    ProductId productId = ProductIdStub.random();
    ProductName productName = ProductNameStub.random();
    DomainEvent domainEvent = ProductCreatedDomainEventStub.create(storeroomId, productId, productName);
    AddProductCommand command = AddProductCommandStub.create(storeroomId, productId, productName);

    // when
    productCreator.execute(command);

    //then
    shouldPublishEventDomains(domainEvent);
  }

  private void shouldSaveProduct(Product product) {
    verify(storeroomRepository).save(product);
  }

  private void shouldPublishEventDomains(DomainEvent domainEvent) {
    verify(domainEventPublisher).publish(Collections.singletonList(domainEvent));
  }

}
