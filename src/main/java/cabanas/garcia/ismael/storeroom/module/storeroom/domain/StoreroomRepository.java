package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.Optional;

public interface StoreroomRepository {
  void save(Storeroom storeroom);

  Optional<Storeroom> findById(StoreroomId storeroomId);

  void update(Storeroom storeroom);
}
