package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.repository.jooq.autogen.Tables;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import org.jooq.DSLContext;

import java.util.Optional;
import java.util.UUID;

public class PostgresStoreroomRepository implements StoreroomRepository {
  private final DSLContext dslContext;

  public PostgresStoreroomRepository(DSLContext dslContext) {
    this.dslContext = dslContext;
  }

  @Override
  public void save(final Storeroom storeroom) {
    dslContext.insertInto(Tables.STOREROOMS)
            .set(Tables.STOREROOMS.S_ID, UUID.fromString(storeroom.getId().getValue()))
            .set(Tables.STOREROOMS.S_NAME, storeroom.getName().getName())
            .returning()
            .fetchOne();
  }

  @Override
  public Optional<Storeroom> findById(StoreroomId storeroomId) {
    return Optional.empty();
  }

  @Override
  public void update(Storeroom storeroom) {

  }
}
