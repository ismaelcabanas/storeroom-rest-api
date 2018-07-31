package cabanas.garcia.ismael.storeroom.product.application;

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

public class NewProductApplicationServiceShould {

  @Mock
  private ProductRepository productRepository;

  private NewProductApplicationService newProductApplicationService;

  @Before
  public void setUp() {
    // With this call to initMocks we tell Mockito to process the annotations
    MockitoAnnotations.initMocks(this);
    newProductApplicationService = new NewProductApplicationService(productRepository);
  }

  @Test
  public void returnCreatedProduct() {
    // given
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);
    Product newProduct = Product.product().withName(productName).withId(productId).build();
    given(productRepository.create(product)).willReturn(newProduct);

    // when
    Product actual = newProductApplicationService.execute(productId, productName);

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
    Product newProduct = Product.product().withName(productName).withId(productId).build();
    given(productRepository.create(product)).willReturn(newProduct);

    // when
    newProductApplicationService.execute(productId, productName);

    // then
    verify(productRepository).create(product);
  }
}
