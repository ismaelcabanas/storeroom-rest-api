package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ProductsShould {

  @Test
  public void be_a_value_object() {
    // when
    Products products = new Products();

    // then
    assertThat(products).isInstanceOf(ValueObject.class);

  }

  @Test
  public void be_equals() {
    // given
    Product product = ProductStub.random();
    Products productsOne = new Products();
    productsOne.add(product);

    Products productsTwo = new Products();
    productsTwo.add(product);

    // when
    boolean actual = productsOne.sameValueAs(productsTwo);

    // then
    assertThat(actual).isTrue();
  }

  @Test
  public void be_not_equals() {
    // given
    Product productOne = ProductStub.random();
    Products productsOne = new Products();
    productsOne.add(productOne);

    Product productTwo = ProductStub.random();
    Products productsTwo = new Products();
    productsTwo.add(productTwo);

    // when
    boolean actual = productsOne.sameValueAs(productsTwo);

    // then
    assertThat(actual).isFalse();
  }

  @Test
  public void add_product() {
    // given
    Product product = ProductStub.random();
    Products products = new Products();

    // when
    Products actual = products.add(product);

    // then
    assertThat(actual.contains(product)).isTrue();
  }

  @Test
  public void add_product_in_added_state() {
    // given
    Product product = ProductStub.random();
    Products products = new Products();

    // when
    Products actual = products.add(product);

    // then
    Product productAdded = actual.find(product.id());
    assertThat(productAdded.getState()).isEqualTo(TrackingState.ADDED);
  }

  @Test
  public void find_a_product_when_exist() {
    // given
    Products products = new Products();
    Product product = ProductStub.random();
    products.add(product);

    // when
    Product actual = products.find(product.id());

    // then
    assertThat(actual).isEqualTo(product);
  }

  @Test
  public void throw_exception_when_find_a_product_that_not_exist() {
    // given
    Products products = new Products();

    // when
    Throwable thrown = catchThrowable(() -> products.find(ProductIdStub.random()));

    // then
    assertThat(thrown)
            .isInstanceOf(ProductNotInStoreroomException.class);
  }
}
