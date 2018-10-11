package cabanas.garcia.ismael.storeroom.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class StoreroomShould {

  private static final StoreroomName SOME_NAME = StoreroomNameStub.random();
  private static final StoreroomId SOME_ID = StoreroomIdStub.random();

  @Test
  public void have_a_name() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();

    // when
    StoreroomName name = storeroom.getName();

    // then
    assertThat(name).isEqualTo(SOME_NAME);
  }

  @Test
  public void have_an_id() {
    // given
    Storeroom storeroom = Storeroom.builder()
            .withId(SOME_ID)
            .withName(SOME_NAME)
            .build();

    // when
    StoreroomId id = storeroom.getId();

    // then
    assertThat(id).isEqualTo(SOME_ID);
  }

  @Test
  public void throw_exception_when_name_is_not_set() {
    // when
    Throwable thrown = catchThrowable(() -> Storeroom.builder()
            .withId(SOME_ID)
            .build());

    // then
    assertThat(thrown)
            .isInstanceOf(NullPointerException.class)
            .hasMessage("StoreroomName should not be null");
  }

  @Test
  public void throw_exception_when_id_is_not_set() {
    // when
    Throwable thrown = catchThrowable(() -> Storeroom.builder()
            .withName(SOME_NAME)
            .build());

    // then
    assertThat(thrown)
            .isInstanceOf(NullPointerException.class)
            .hasMessage("StoreroomId should not be null");
  }
}
