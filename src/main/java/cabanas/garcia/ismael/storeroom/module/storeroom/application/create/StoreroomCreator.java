package cabanas.garcia.ismael.storeroom.module.storeroom.application.create;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;

public class StoreroomCreator {
  private final StoreroomRepository repository;

  public StoreroomCreator(StoreroomRepository repository) {
    this.repository = repository;
  }

  public void execute(StoreroomCreateCommand command) {
    repository.save(Storeroom.builder()
            .withId(new StoreroomId(command.getId()))
            .withName(new StoreroomName(command.getName()))
            .build()
    );
  }
}
