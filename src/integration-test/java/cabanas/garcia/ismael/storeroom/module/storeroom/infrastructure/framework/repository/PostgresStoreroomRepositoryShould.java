package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.DataBaseConfiguration;
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
    Storeroom storeroom = StoreroomStub.random();
    storeroomRepository.save(storeroom);

    // when
    Optional<Storeroom> storeroomResult = storeroomRepository.findById(storeroom.getId());

    // then
    assertThat(storeroomResult).isNotEmpty();
    assertThat(storeroomResult.get().getId()).isEqualTo(storeroom.getId());
    assertThat(storeroomResult.get().getName()).isEqualTo(storeroom.getName());
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
  public void update_storeroom_with_products() {
    // given
    Storeroom anExistingStoreroom = StoreroomStub.random();
    storeroomRepository.save(anExistingStoreroom);
    anExistingStoreroom.addProduct(ProductStub.random());
    anExistingStoreroom.addProduct(ProductStub.random());

    // when
    storeroomRepository.update(anExistingStoreroom);

    // then
    assertThat(JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "STOREROOM_PRODUCTS",
            "SP_STOREROOM_ID = '" + anExistingStoreroom.getId().getValue() + "'"))
            .isEqualTo(2);
  }
}
