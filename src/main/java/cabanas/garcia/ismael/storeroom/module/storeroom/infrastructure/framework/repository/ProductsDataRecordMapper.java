package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.ProductName;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records.StoreroomProductsRecord;
import org.jooq.RecordMapper;

public class ProductsDataRecordMapper implements RecordMapper<StoreroomProductsRecord, Product> {


  @Override
  public Product map(StoreroomProductsRecord record) {
    return Product.builder()
            .withId(new ProductId(record.getSpId().toString()))
            .withName(new ProductName(record.getSpName()))
            .build();
  }
}
