package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeInMemmoryStoreroomRepository implements StoreroomRepository {
  private List<Storeroom> storerooms = new ArrayList<>();

  public FakeInMemmoryStoreroomRepository(List<Storeroom> storeroomList) {
    storeroomList.forEach(storeroom -> storerooms.add(storeroom));
  }

  @Override
  public void save(Storeroom storeroom) {
    storerooms.add(storeroom);
  }

  @Override
  public Optional<Storeroom> findById(StoreroomId storeroomId) {
    return storerooms.stream()
            .filter(storeroom -> storeroom.id().equals(storeroomId))
            .findFirst();
  }

  @Override
  public void update(final Storeroom storeroom) {
    storerooms.remove(storeroom);
    storerooms.add(storeroom);
  }

  public boolean wasProductPersisted(StoreroomId storeroomId, ProductId productId) {
    return storerooms.stream()
            .filter(storeroom -> storeroom.id().equals(storeroomId))
            .flatMap(storeroom -> storeroom.products().getProducts().stream())
            .anyMatch(product -> product.id().equals(productId));
  }

  public int currentProductStock(StoreroomId storeroomId, ProductId productId) {
    return storerooms.stream()
            .filter(storeroom -> storeroom.id().equals(storeroomId))
            .flatMap(storeroom -> storeroom.products().getProducts().stream())
            .filter(product -> product.id().equals(productId))
            .findFirst()
            .orElseThrow(() -> new ProductNotInStoreroomException(productId))
            .stock()
            .getValue();
  }

  public boolean wasStoreroomPersisted(StoreroomId storeroomId) {
    return storerooms.stream()
            .anyMatch(storeroom -> storeroom.id().equals(storeroomId));
  }
}
