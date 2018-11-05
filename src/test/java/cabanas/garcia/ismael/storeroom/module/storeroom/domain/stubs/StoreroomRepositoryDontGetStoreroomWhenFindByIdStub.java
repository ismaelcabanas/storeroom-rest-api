package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

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
