package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.product.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE) // https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-jpa-test
@ActiveProfiles("integration-test")
public class PostgresProductRepositoryShould {

  private ProductRepository productRepository;

  @Autowired
  private ProductCrudRepository productCrudRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void saveProduct() {
    // given
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);
    productRepository = new PostgresProductRepository(productCrudRepository);

    // when
    productRepository.save(product);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "PRODUCTS",
            "P_NAME = '" + productName.getName() + "'"))
            .isEqualTo(1);
  }
}
