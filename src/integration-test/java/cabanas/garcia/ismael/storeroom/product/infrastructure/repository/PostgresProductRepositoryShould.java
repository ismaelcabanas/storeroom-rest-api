package cabanas.garcia.ismael.storeroom.product.infrastructure.repository;

import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.Application;
import cabanas.garcia.ismael.storeroom.product.domain.*;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.configuration.RepositoryConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = Application.class)
@ActiveProfiles("integration-test")
public class PostgresProductRepositoryShould {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void saveProduct() {
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);

    productRepository.create(product);

    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "PRODUCTS",
            "P_NAME = '" + productName.getName() + "'"))
            .isEqualTo(1);
  }
}
