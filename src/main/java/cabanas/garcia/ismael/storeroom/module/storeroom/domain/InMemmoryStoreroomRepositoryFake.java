package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemmoryStoreroomRepositoryFake implements StoreroomRepository {
  private List<Storeroom> storerooms = new ArrayList<>();

  public InMemmoryStoreroomRepositoryFake(List<Storeroom> storeroomList) {
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
}
