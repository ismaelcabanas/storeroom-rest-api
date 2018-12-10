package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository;

import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.DataBaseConfiguration;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Quantity;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Stock;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.ProductStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration.StoreroomRepositoryConfiguration;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@ImportAutoConfiguration({StoreroomRepositoryConfiguration.class, DataBaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration-test")
public class PostgresStoreroomRepositoryShould {
  @Autowired
  private StoreroomRepository storeroomRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void save_storeroom() {
    // given
    StoreroomName storeroomName = StoreroomNameStub.random();
    StoreroomId storeroomId = StoreroomIdStub.random();
    Storeroom storeroom = StoreroomStub.create(storeroomId, storeroomName);

    // when
    storeroomRepository.save(storeroom);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "STOREROOMS",
            "S_NAME = '" + storeroomName.getName() + "'"))
            .isEqualTo(1);
  }

  @Test
  public void find_existent_storeroom() {
    // given
    Storeroom storeroom = StoreroomStub.emptyStoreroom();
    storeroomRepository.save(storeroom);

    // when
    Optional<Storeroom> storeroomResult = storeroomRepository.findById(storeroom.id());

    // then
    assertThat(storeroomResult).isNotEmpty();
    assertThat(storeroomResult.get().id()).isEqualTo(storeroom.id());
    assertThat(storeroomResult.get().name()).isEqualTo(storeroom.name());
  }

  @Test
  public void not_find_inexistent_storeroom() {
    // given
    StoreroomId storeroomIdThatNotExistInSystem = new StoreroomId(UUID.randomUUID().toString());

    // when
    Optional<Storeroom> storeroomResult = storeroomRepository.findById(storeroomIdThatNotExistInSystem);

    // then
    assertThat(storeroomResult).isEmpty();
  }

  @Test
  public void add_products_in_storeroom() {
    // given
    Storeroom anExistingStoreroom = StoreroomStub.emptyStoreroom();
    storeroomRepository.save(anExistingStoreroom);
    anExistingStoreroom.addProduct(ProductStub.random());
    anExistingStoreroom.addProduct(ProductStub.random());

    // when
    storeroomRepository.update(anExistingStoreroom);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "STOREROOM_PRODUCTS",
            "SP_STOREROOM_ID = '" + anExistingStoreroom.id().getValue() + "'"))
            .isEqualTo(2);
  }

  @Test
  public void update_stock_product_in_storeroom() {
    // given
    Storeroom anExistingStoreroom = StoreroomStub.emptyStoreroom();
    storeroomRepository.save(anExistingStoreroom);
    Product product = ProductStub.random();
    anExistingStoreroom.addProduct(product);
    storeroomRepository.update(anExistingStoreroom);
    Quantity quantity = Quantity.builder().withValue(3).build();
    anExistingStoreroom.reFill(product.id(), quantity);

    // when
    storeroomRepository.update(anExistingStoreroom);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "STOREROOM_PRODUCTS",
            "SP_STOREROOM_ID = '"
                    + anExistingStoreroom.id().getValue()
                    + "' AND SP_ID='"
                    + product.id().getValue() + "' AND SP_STOCK=" + quantity.getValue()))
            .isEqualTo(1);
  }

  @Test
  public void find_storeroom_with_product_with_two_units_of_stock() {
    // given
    Storeroom currentStoreroom = StoreroomStub.emptyStoreroom();
    storeroomRepository.save(currentStoreroom);
    Product product = ProductStub.random();
    currentStoreroom.addProduct(product);
    storeroomRepository.update(currentStoreroom);
    currentStoreroom.reFill(product.id(), Quantity.builder().withValue(2).build());
    storeroomRepository.update(currentStoreroom);

    // when
    Storeroom storeroom = storeroomRepository.findById(currentStoreroom.id()).get();

    // then
    assertThat(storeroom.products().getProducts()).isNotEmpty();
    assertThat(storeroom.products().size()).isEqualTo(1);
    assertThat(storeroom.products().find(product.id()).stock()).isEqualTo(Stock.builder().withValue(2).build());
    assertThat(storeroom.products().find(product.id()).name()).isEqualTo(product.name());
  }
}
