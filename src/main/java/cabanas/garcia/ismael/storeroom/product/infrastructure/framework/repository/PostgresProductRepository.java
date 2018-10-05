package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductRepository;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Tables;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records.ProductsRecord;
import org.jooq.DSLContext;

import java.util.UUID;

public class PostgresProductRepository implements ProductRepository {

  private final DSLContext dslContext;
  private final ProductDataRecordMapper productDataRecordMapper;

  public PostgresProductRepository(DSLContext dslContext, ProductDataRecordMapper productDataRecordMapper) {
    this.dslContext = dslContext;
    this.productDataRecordMapper = productDataRecordMapper;
  }

  @Override
  public Product save(final Product product) {
    ProductsRecord record = dslContext.insertInto(Tables.PRODUCTS)
            .set(Tables.PRODUCTS.P_ID, UUID.fromString(product.getId()))
            .set(Tables.PRODUCTS.P_NAME, product.getName())
            .returning()
            .fetchOne();
    return productDataRecordMapper.map(record);
  }
}
