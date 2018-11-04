package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.Optional;

public class StoreroomRepositoryDontGetStoreroomWhenFindByIdStub implements StoreroomRepository {
  @Override
  public void save(Storeroom storeroom) {

  }

  @Override
  public Optional<Storeroom> findById(StoreroomId storeroomId) {
    return Optional.empty();
  }

  @Override
  public void update(Storeroom storeroom) {

  }
}
