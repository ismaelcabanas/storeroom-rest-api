package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.framework.repository;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.TrackingState;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.UUID;

import static cabanas.garcia.ismael.storeroom.module.product.infrastructure.framework.repository.jooq.autogen.Tables.STOREROOMS;
import static cabanas.garcia.ismael.storeroom.product.infrastructure.framework.repository.jooq.autogen.Tables.STOREROOM_PRODUCTS;

public class PostgresStoreroomRepository implements StoreroomRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresStoreroomRepository.class.getName());

  private final DSLContext dslContext;
  private final StoreroomDataRecordMapper storeroomDataRecordMapper;

  public PostgresStoreroomRepository(DSLContext dslContext,
                                     StoreroomDataRecordMapper storeroomDataRecordMapper) {
    this.dslContext = dslContext;
    this.storeroomDataRecordMapper = storeroomDataRecordMapper;
  }

  @Override
  public void save(final Storeroom storeroom) {
    dslContext.insertInto(STOREROOMS)
            .set(STOREROOMS.S_ID, UUID.fromString(storeroom.id().getValue()))
            .set(STOREROOMS.S_NAME, storeroom.getName().getName())
            .returning()
            .fetchOne();
    LOGGER.debug("Storeroom {} saved", storeroom);
  }

  private void save(final StoreroomId storeroomId, final Product product) {
    dslContext.insertInto(STOREROOM_PRODUCTS)
            .set(STOREROOM_PRODUCTS.SP_ID, UUID.fromString(product.id().getValue()))
            .set(STOREROOM_PRODUCTS.SP_NAME, product.name().getName())
            .set(STOREROOM_PRODUCTS.SP_STOREROOM_ID, UUID.fromString(storeroomId.getValue()))
            .set(STOREROOM_PRODUCTS.SP_CREATION, DSL.currentTimestamp())
            .set(STOREROOM_PRODUCTS.SP_MODIFICATION, DSL.currentTimestamp())
            .returning()
            .fetchOne();
    LOGGER.debug("Product {} saved in storeroom {}", product, storeroomId);
  }

  @Override
  public Optional<Storeroom> findById(final StoreroomId storeroomId) {
    return dslContext.selectFrom(STOREROOMS)
            .where(STOREROOMS.S_ID.eq(UUID.fromString(storeroomId.getValue())))
            .fetchOptional(storeroomDataRecordMapper);
  }

  @Override
  public void update(final Storeroom storeroom) {
    LOGGER.debug("Updating {}", storeroom);
    storeroom.products().getProducts().stream()
            .filter(product -> product.getState() == TrackingState.ADDED)
            .forEach(product -> save(storeroom.id(), product));
  }

}
