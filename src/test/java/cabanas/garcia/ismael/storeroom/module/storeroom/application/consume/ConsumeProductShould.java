package cabanas.garcia.ismael.storeroom.module.storeroom.application.consume;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.InMemmoryStoreroomRepositoryFake;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductConsumedDomainEvent;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductNotInStoreroomException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ConsumeProductShould {

  private static final int FIVE = 5;
  private static final String SOME_NOT_EXIST_STOREROOM_ID = StoreroomIdStub.random().getValue();
  private static final String SOME_NOT_EXIST_PRODUCT_ID = ProductIdStub.random().getValue();

  private Storeroom someStoreroom;
  private Product someProduct;
  private Quantity currentProductQuantity = Quantity.builder().withValue(FIVE).build();

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private DomainEventPublisher domainEventPublisher;

  @Captor
  private ArgumentCaptor<Collection<DomainEvent>> argumentCaptor;

  private StoreroomRepository storeroomRepository;

  @Before
  public void setUp() {
    someProduct = ProductStub.random();
    someStoreroom = StoreroomStub.emptyStoreroom();
    someStoreroom.addProduct(someProduct);
    someStoreroom.reFill(someProduct.id(), currentProductQuantity);
    storeroomRepository = new InMemmoryStoreroomRepositoryFake(
            Arrays.asList(someStoreroom)
    );
  }

  @Test
  public void publish_product_consumed_domain_event() {
    // given
    ConsumeProduct consumeProduct = new ConsumeProduct(storeroomRepository, domainEventPublisher);
    int quantityToConsumeLessThanCurrentProductQuantity = currentProductQuantity.getValue() - 2;
    ConsumeProductCommand command = ConsumeProductCommand.builder()
            .withProductId(someProduct.id().getValue())
            .withQuantity(quantityToConsumeLessThanCurrentProductQuantity)
            .withStoreroomId(someStoreroom.id().getValue())
            .build();

    // when
    consumeProduct.execute(command);

    // then
    verify(domainEventPublisher).publish(argumentCaptor.capture());
    assertThat(argumentCaptor.getValue())
            .containsAnyOf(ProductConsumedDomainEvent.builder()
                    .withProductId(someProduct.id().getValue())
                    .withQuantity(quantityToConsumeLessThanCurrentProductQuantity)
                    .build());
  }

  @Test
  public void update_stock_product() {
    // given
    StoreroomRepository storeroomRepositorySpy = Mockito.spy(storeroomRepository);
    ConsumeProduct consumeProduct = new ConsumeProduct(storeroomRepositorySpy, domainEventPublisher);
    int quantityToConsumeLessThanCurrentProductQuantity = currentProductQuantity.getValue() - 2;
    ConsumeProductCommand command = ConsumeProductCommand.builder()
            .withProductId(someProduct.id().getValue())
            .withQuantity(quantityToConsumeLessThanCurrentProductQuantity)
            .withStoreroomId(someStoreroom.id().getValue())
            .build();

    // when
    consumeProduct.execute(command);

    // then
    verify(storeroomRepositorySpy).update(any());
  }

  @Test
  public void throw_exception_when_does_not_exist_storeroom_to_consume_product_quantity() {
    // given
    ConsumeProduct consumeProduct = new ConsumeProduct(storeroomRepository, domainEventPublisher);
    int quantityToConsumeLessThanCurrentProductQuantity = currentProductQuantity.getValue() - 2;
    ConsumeProductCommand command = ConsumeProductCommand.builder()
            .withProductId(someProduct.id().getValue())
            .withQuantity(quantityToConsumeLessThanCurrentProductQuantity)
            .withStoreroomId(SOME_NOT_EXIST_STOREROOM_ID)
            .build();

    // when
    Throwable thrown = catchThrowable(() -> consumeProduct.execute(command));

    // then
    assertThat(thrown).isInstanceOf(StoreroomNotFoundException.class);
  }

  @Test
  public void throw_exception_when_does_not_exist_product_from_consume() {
    // given
    ConsumeProduct consumeProduct = new ConsumeProduct(storeroomRepository, domainEventPublisher);
    int quantityToConsumeLessThanCurrentProductQuantity = currentProductQuantity.getValue() - 2;
    ConsumeProductCommand command = ConsumeProductCommand.builder()
            .withProductId(SOME_NOT_EXIST_PRODUCT_ID)
            .withQuantity(quantityToConsumeLessThanCurrentProductQuantity)
            .withStoreroomId(someStoreroom.id().getValue())
            .build();

    // when
    Throwable thrown = catchThrowable(() -> consumeProduct.execute(command));

    // then
    assertThat(thrown).isInstanceOf(ProductNotInStoreroomException.class);
  }
}
