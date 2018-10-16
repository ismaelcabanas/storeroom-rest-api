package cabanas.garcia.ismael.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Tables;
import org.jooq.DSLContext;

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
}
