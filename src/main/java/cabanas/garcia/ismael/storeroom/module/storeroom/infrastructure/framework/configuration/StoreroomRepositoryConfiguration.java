package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.PostgresStoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.StoreroomDataRecordMapper;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreroomRepositoryConfiguration {

  @Bean
  public StoreroomDataRecordMapper storeroomDataRecordMapper() {
    return new StoreroomDataRecordMapper();
  }

  @Bean
  public StoreroomRepository storeroomRepository(DSLContext dslContext,
                                                 StoreroomDataRecordMapper storeroomDataRecordMapper) {
    return new PostgresStoreroomRepository(dslContext, storeroomDataRecordMapper);
  }
}
