package cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.configuration;

import cabanas.garcia.ismael.storeroom.module.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.module.product.infrastructure.repository.PostgresProductRepository;
import cabanas.garcia.ismael.storeroom.module.product.infrastructure.repository.ProductDataRecordMapper;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRepositoryConfiguration {

  @Bean
  public ProductDataRecordMapper productDataRecordMapper() {
    return new ProductDataRecordMapper();
  }

  @Bean
  public ProductRepository productRepository(DSLContext dslContext, ProductDataRecordMapper productDataRecordMapper) {
    return new PostgresProductRepository(dslContext, productDataRecordMapper);
  }
}
