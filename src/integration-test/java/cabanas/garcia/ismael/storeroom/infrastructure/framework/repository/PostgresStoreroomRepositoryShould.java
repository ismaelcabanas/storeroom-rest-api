package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.domain.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.domain.StoreroomName;
import cabanas.garcia.ismael.storeroom.domain.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.domain.StoreroomStub;
import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.StoreroomRepositoryConfiguration;
import cabanas.garcia.ismael.storeroom.infrastructure.framework.configuration.DataBaseConfiguration;
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
@ImportAutoConfiguration({StoreroomRepositoryConfiguration.class, DataBaseConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration-test")
public class PostgresStoreroomRepositoryShould {
  @Autowired
  private StoreroomRepository storeroomRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void saveStoreroom() {
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
}
