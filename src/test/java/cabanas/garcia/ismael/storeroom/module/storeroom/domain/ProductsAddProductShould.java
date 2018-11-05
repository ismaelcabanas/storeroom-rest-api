package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ProductsAddProductShould {

  @Test
  public void add_product_to_list() {
    // given
    Product product = ProductStub.random();
    Products products = new Products();

    // when
    Products newProducts = products.add(product);

    // then
    assertThat(newProducts.contains(product));
  }

  @Test
  public void throw_exception_when_add_null_product() {
    // given
    Products products = new Products();

    // when
    Throwable thrown = catchThrowable(() -> products.add(null));

    // then
    assertThat(thrown).isInstanceOf(NullPointerException.class);
  }
}
