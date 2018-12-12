package cabanas.garcia.ismael.storeroom.module.storeroom.application.create;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.FakeInMemmoryStoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static java.util.Collections.EMPTY_LIST;
import static org.assertj.core.api.Assertions.assertThat;

public class StoreroomCreatorShould {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  private StoreroomCreator storeroomCreator;

  private FakeInMemmoryStoreroomRepository storeroomRepository;

  @Before
  public void setUp() {
    storeroomRepository = new FakeInMemmoryStoreroomRepository(EMPTY_LIST);
    storeroomCreator = new StoreroomCreator(storeroomRepository);
  }

  @Test
  public void create_storeroom() {
    // given
    StoreroomId storeroomId = StoreroomIdStub.random();
    StoreroomName storeroomName = StoreroomNameStub.random();
    Storeroom storeroom = StoreroomStub.create(storeroomId, storeroomName);
    StoreroomCreateCommand command = StoreroomCreateCommandStub.create(storeroomId.getValue(), storeroomName.getName());

    // when
    storeroomCreator.execute(command);

    //then
    assertThat(storeroomRepository.wasStoreroomPersisted(storeroom.id())).isTrue();
  }

}
