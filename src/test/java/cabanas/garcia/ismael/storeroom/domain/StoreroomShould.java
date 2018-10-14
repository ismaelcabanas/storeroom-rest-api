package cabanas.garcia.ismael.storeroom.domain;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductIdStub;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.product.domain.ProductNameStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StoreroomShould {

  private static final StoreroomName SOME_NAME = StoreroomNameStub.random();
  private static final StoreroomId SOME_ID = StoreroomIdStub.random();
  private static final ProductId SOME_PRODUCT_ID = ProductIdStub.random();
  private static final ProductName SOME_PRODUCT_NAME = ProductNameStub.random();
  private static final ProductId SOME_NOT_EXIST_PRODUCT_ID = ProductIdStub.random();

  @Test
  public void have_a_name() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();

    // when
    StoreroomName name = storeroom.getName();

    // then
    assertThat(name).isEqualTo(SOME_NAME);
  }

  @Test
  public void have_an_id() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();

    // when
    StoreroomId id = storeroom.getId();

    // then
    assertThat(id).isEqualTo(SOME_ID);
  }

  @Test
  public void throw_exception_when_name_is_not_set() {
    // when
    Throwable thrown = catchThrowable(() -> Storeroom.builder()
            .withId(SOME_ID)
            .build());

    // then
    assertThat(thrown)
            .isInstanceOf(NullPointerException.class)
            .hasMessage("StoreroomName should not be null");
  }

  @Test
  public void throw_exception_when_id_is_not_set() {
    // when
    Throwable thrown = catchThrowable(() -> Storeroom.builder()
            .withName(SOME_NAME)
            .build());

    // then
    assertThat(thrown)
            .isInstanceOf(NullPointerException.class)
            .hasMessage("StoreroomId should not be null");
  }

  @Test
  public void have_empty_product_list_when_create_storeroom() {
    // when
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();

    // then
    assertThat(storeroom.isEmpty()).isTrue();
  }

  @Test
  public void add_product_to_storeroom() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();
    Product product = Product.product()
            .withId(SOME_PRODUCT_ID)
            .withName(SOME_PRODUCT_NAME)
            .build();

    // when
    storeroom.addProduct(product);

    // then
    assertThat(storeroom.productsSize()).isEqualTo(new Size(1));
  }

  @Test
  public void throw_exception_when_add_existent_product_to_storeroom() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();
    Product product = Product.product()
            .withId(SOME_PRODUCT_ID)
            .withName(SOME_PRODUCT_NAME)
            .build();
    storeroom.addProduct(product);

    // when
    Throwable thrown = catchThrowable(() -> storeroom.addProduct(product));

    // then
    assertThat(thrown)
            .isInstanceOf(ProductAlreadyExistException.class)
            .hasMessage("The product " + SOME_PRODUCT_ID.getValue() + " already exist.");
  }

  @Test
  public void throw_exception_when_get_product_from_storeroom_and_not_exist() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();
    Product product = Product.product()
            .withId(SOME_PRODUCT_ID)
            .withName(SOME_PRODUCT_NAME)
            .build();
    storeroom.addProduct(product);

    // when
    Throwable thrown = catchThrowable(() -> storeroom.product(SOME_NOT_EXIST_PRODUCT_ID));

    // then
    assertThat(thrown)
            .isInstanceOf(ProductNotExistException.class)
            .hasMessage("The product " + SOME_NOT_EXIST_PRODUCT_ID.getValue() + " doesn't exist.");
  }

  @Test
  public void get_product_from_storeroom() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();
    Product productInStoreroom = Product.product()
            .withId(SOME_PRODUCT_ID)
            .withName(SOME_PRODUCT_NAME)
            .build();
    storeroom.addProduct(productInStoreroom);

    // when
    Product product = storeroom.product(SOME_PRODUCT_ID);

    // then
    assertThat(product).isEqualTo(productInStoreroom);
  }
}
