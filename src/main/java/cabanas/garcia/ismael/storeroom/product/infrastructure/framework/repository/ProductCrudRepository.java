package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductCrudRepository extends CrudRepository<ProductEntity, String> {
}
