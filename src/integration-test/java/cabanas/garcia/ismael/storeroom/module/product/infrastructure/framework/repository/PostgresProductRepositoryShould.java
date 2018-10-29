package cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.module.product.domain.Product;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductIdStub;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductNameStub;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.module.product.domain.ProductStub;
import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.DataBaseConfiguration;
import cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.configuration.ProductRepositoryConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ImportAutoConfiguration({ProductRepositoryConfiguration.class, DataBaseConfiguration.class})
// https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#
// boot-features-testing-spring-boot-applications-testing-autoconfigured-jpa-test
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration-test")
public class PostgresProductRepositoryShould {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void saveProduct() {
    // given
    ProductName productName = ProductNameStub.random();
    ProductId productId = ProductIdStub.random();
    Product product = ProductStub.create(productId, productName);

    // when
    productRepository.save(product);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "PRODUCTS",
            "P_NAME = '" + productName.getName() + "'"))
            .isEqualTo(1);
  }
}
