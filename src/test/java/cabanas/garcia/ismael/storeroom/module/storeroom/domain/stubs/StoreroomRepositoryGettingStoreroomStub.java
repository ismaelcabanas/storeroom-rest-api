package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

import java.util.Optional;

public class StoreroomRepositoryGettingStoreroomStub implements StoreroomRepository {
  private Storeroom storeroom;

  public StoreroomRepositoryGettingStoreroomStub(Storeroom theStoreroom) {
    this.storeroom = theStoreroom;
  }

  @Override
  public void save(Storeroom theStoreroom) {

  }

  @Override
  public Optional<Storeroom> findById(StoreroomId storeroomId) {
    return Optional.of(storeroom);
  }

  @Override
  public void update(Storeroom theStoreroom) {
  }

}
