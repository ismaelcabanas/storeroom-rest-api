package cabanas.garcia.ismael.storeroom.product.application.newproduct;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductIdStub;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.product.domain.ProductNameStub;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.product.domain.ProductStub;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class NewProductShould {

  @Mock
  private ProductRepository productRepository;

  private NewProduct newProduct;

  @Before
  public void setUp() {
    // With this call to initMocks we tell Mockito to process the annotations
    MockitoAnnotations.initMocks(this);
    newProduct = new NewProduct(productRepository);
  }

  @Test
  public void returnCreatedProduct() {
    // given
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);
    given(productRepository.save(product)).willReturn(product);

    // when
    Product actual = this.newProduct.execute(productId, productName);

    // then
    assertThat(actual).isNotNull();
    assertThat(actual.getId()).isNotNull();
    assertThat(actual.getName()).isEqualTo(productName.getName());
  }

  @Test
  public void persistProduct() {
    // given
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);
    given(productRepository.save(product)).willReturn(product);

    // when
    this.newProduct.execute(productId, productName);

    // then
    verify(productRepository).save(product);
  }
}
