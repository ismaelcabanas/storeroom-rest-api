package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.product.domain.Product;
import cabanas.garcia.ismael.storeroom.product.domain.ProductId;
import cabanas.garcia.ismael.storeroom.product.domain.ProductName;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.tables.records.ProductsRecord;
import org.jooq.RecordMapper;

public class ProductDataRecordMapper implements RecordMapper<ProductsRecord, Product> {
  @Override
  public Product map(ProductsRecord record) {
    return Product.product()
            .withId(ProductId.productId().withId(record.getPId().toString()).build())
            .withName(ProductName.productName().withName(record.getPName()).build())
            .build();
  }
}
