package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.PostgresStoreroomRepository;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreroomRepositoryConfiguration {

  @Bean
  public StoreroomRepository storeroomRepository(DSLContext dslContext) {
    return new PostgresStoreroomRepository(dslContext);
  }
}
