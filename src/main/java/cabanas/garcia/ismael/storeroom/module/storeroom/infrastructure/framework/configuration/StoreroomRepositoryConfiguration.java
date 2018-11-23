package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.PostgresStoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.StoreroomDataRecordMapper;
import cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository.ProductsDataRecordMapper;
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
  public ProductsDataRecordMapper storeroomProductsDataRecordMapper() {
    return new ProductsDataRecordMapper();
  }

  @Bean
  public StoreroomRepository storeroomRepository(DSLContext dslContext,
                                                 StoreroomDataRecordMapper storeroomDataRecordMapper,
                                                 ProductsDataRecordMapper productsDataRecordMapper) {
    return new PostgresStoreroomRepository(dslContext, storeroomDataRecordMapper, productsDataRecordMapper);
  }
}
