package cabanas.garcia.ismael.storeroom.module.storeroom.application.refill;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductRefilledDomainEvent;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.QuantityStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomRepositoryDontGetStoreroomWhenFindByIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomRepositoryGettingStoreroomStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;

public class RefillProductShould {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private DomainEventPublisher domainEventPublisher;

  @Test
  public void update_storeroom() {
    // given
    Product product = ProductStub.random();
    Storeroom storeroom = StoreroomStub.createWithProducts(product);

    StoreroomRepositoryGettingStoreroomStub storeroomRepository =
            new StoreroomRepositoryGettingStoreroomStub(storeroom);
    StoreroomRepository storeroomRepositorySpy = Mockito.spy(storeroomRepository);

    RefillProduct refillProduct = new RefillProduct(storeroomRepositorySpy, domainEventPublisher);
    RefillProductCommand command = RefillProductCommand.builder()
            .withStoreroomId(storeroom.id().getValue())
            .withProductId(product.id().getValue())
            .withQuantity(QuantityStub.random().getValue())
            .build();

    // when
    refillProduct.execute(command);

    // then
    verify(storeroomRepositorySpy).update(storeroom);
  }

  @Test
  public void publish_domain_events() {
    // given
    Product product = ProductStub.random();
    Storeroom storeroom = StoreroomStub.createWithProducts(product);

    StoreroomRepositoryGettingStoreroomStub storeroomRepository =
            new StoreroomRepositoryGettingStoreroomStub(storeroom);

    RefillProduct refillProduct = new RefillProduct(storeroomRepository, domainEventPublisher);
    int quantity = QuantityStub.random().getValue();
    RefillProductCommand command = RefillProductCommand.builder()
            .withStoreroomId(storeroom.id().getValue())
            .withProductId(product.id().getValue())
            .withQuantity(quantity)
            .build();

    DomainEvent domainEvent =
            ProductRefilledDomainEvent.builder()
                    .withProductId(product.id().getValue())
                    .withQuantity(quantity)
                    .build();

    // when
    refillProduct.execute(command);

    // then
    verify(domainEventPublisher).publish(Collections.singletonList(domainEvent));
  }

  @Test
  public void throw_exception_when_refill_product_on_not_existent_storeroom() {
    // given
    Product product = ProductStub.random();
    Storeroom storeroom = StoreroomStub.createWithProducts(product);

    StoreroomRepository storeroomRepository =
            new StoreroomRepositoryDontGetStoreroomWhenFindByIdStub();

    RefillProduct refillProduct = new RefillProduct(storeroomRepository, domainEventPublisher);
    RefillProductCommand command = RefillProductCommand.builder()
            .withStoreroomId(storeroom.id().getValue())
            .withProductId(product.id().getValue())
            .withQuantity(QuantityStub.random().getValue())
            .build();

    // when
    Throwable thrown = catchThrowable(() -> refillProduct.execute(command));

    // then
    assertThat(thrown)
            .isInstanceOf(StoreroomNotFoundException.class);
  }
}
