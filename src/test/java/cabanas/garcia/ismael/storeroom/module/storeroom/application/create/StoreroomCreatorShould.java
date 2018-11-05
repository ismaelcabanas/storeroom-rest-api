package cabanas.garcia.ismael.storeroom.module.storeroom.application.create;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomIdStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomNameStub;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs.StoreroomStub;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class StoreroomCreatorShould {

  @Rule
  public MockitoRule mockitoRule = MockitoJUnit.rule();

  private StoreroomCreator storeroomCreator;

  @Mock
  private StoreroomRepository storeroomRepository;

  @Before
  public void setUp() {
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
    shouldSaveStoreroom(storeroom);
  }

  private void shouldSaveStoreroom(Storeroom storeroom) {
    verify(storeroomRepository).save(storeroom);
  }
}
