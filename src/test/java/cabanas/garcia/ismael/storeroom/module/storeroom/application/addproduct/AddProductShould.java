package cabanas.garcia.ismael.storeroom.module.storeroom.application.addproduct;

import cabanas.garcia.ismael.shared.domain.event.DomainEvent;
import cabanas.garcia.ismael.shared.domain.event.DomainEventPublisher;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomNotFoundException;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductCreatedDomainEventStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
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

public class AddProductShould {
  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock
  private DomainEventPublisher domainEventPublisher;

  @Test
  public void update_storeroom_with_new_product() {
    // given
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());
    StoreroomRepositoryGettingStoreroomStub storeroomRepository =
            new StoreroomRepositoryGettingStoreroomStub(storeroom);
    StoreroomRepository storeroomRepositorySpy = Mockito.spy(storeroomRepository);
    AddProduct addProduct = new AddProduct(storeroomRepositorySpy, domainEventPublisher);
    AddProductCommand command =
            AddProductCommandStub.create(storeroom.getId(), product.getId(), product.getName());

    // when
    addProduct.execute(command);

    //then
    verify(storeroomRepositorySpy).update(storeroom);
  }

  @Test
  public void publish_domain_event() {
    // given
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());
    StoreroomRepositoryGettingStoreroomStub storeroomRepository =
            new StoreroomRepositoryGettingStoreroomStub(storeroom);
    AddProduct addProduct = new AddProduct(storeroomRepository, domainEventPublisher);
    AddProductCommand command =
            AddProductCommandStub.create(storeroom.getId(), product.getId(), product.getName());
    DomainEvent domainEvent =
            ProductCreatedDomainEventStub.create(storeroom.getId(), product.getId(), product.getName());

    // when
    addProduct.execute(command);

    //then
    verify(domainEventPublisher).publish(Collections.singletonList(domainEvent));
  }

  @Test
  public void throw_exception_when_not_exist_storeroom_to_add_product() {
    // given
    AddProduct addProduct = new AddProduct(new StoreroomRepositoryDontGetStoreroomWhenFindByIdStub(), domainEventPublisher);
    Storeroom storeroom = StoreroomStub.create(StoreroomIdStub.random(), StoreroomNameStub.random());
    Product product = ProductStub.create(ProductIdStub.random(), ProductNameStub.random());
    AddProductCommand command =
            AddProductCommandStub.create(storeroom.getId(), product.getId(), product.getName());

    // when
    Throwable thrown = catchThrowable(() -> addProduct.execute(command));

    // then
    assertThat(thrown)
            .isInstanceOf(StoreroomNotFoundException.class);
  }

}
