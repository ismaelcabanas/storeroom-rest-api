package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
