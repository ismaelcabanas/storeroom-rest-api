package cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductEntity {
  @Id
  private String id;

  @Column(unique = true, nullable = false, length = 50)
  private String name;

  public ProductEntity(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
