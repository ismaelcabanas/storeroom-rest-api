package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Products;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomRepository;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static cabanas.garcia.ismael.storeroom.module.product.infrastructure.repository.jooq.autogen.Tables.STOREROOMS;
import static cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository.jooq.autogen.Tables.STOREROOM_PRODUCTS;

public class PostgresStoreroomRepository implements StoreroomRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresStoreroomRepository.class.getName());

  private final DSLContext dslContext;
  private final StoreroomDataRecordMapper storeroomDataRecordMapper;
  private final ProductsDataRecordMapper productsDataRecordMapper;

  public PostgresStoreroomRepository(DSLContext dslContext,
                                     StoreroomDataRecordMapper storeroomDataRecordMapper,
                                     ProductsDataRecordMapper productsDataRecordMapper) {
    this.dslContext = dslContext;
    this.storeroomDataRecordMapper = storeroomDataRecordMapper;
    this.productsDataRecordMapper = productsDataRecordMapper;
  }

  @Override
  public void save(final Storeroom storeroom) {
    dslContext.insertInto(STOREROOMS)
            .set(STOREROOMS.S_ID, UUID.fromString(storeroom.id().getValue()))
            .set(STOREROOMS.S_NAME, storeroom.name().getName())
            .returning()
            .fetchOne();
    LOGGER.debug("Storeroom {} saved", storeroom);
  }

  private void save(final StoreroomId storeroomId, final Product product) {
    switch (product.getState()) {
      case ADDED:
        dslContext.insertInto(STOREROOM_PRODUCTS)
                .set(STOREROOM_PRODUCTS.SP_ID, UUID.fromString(product.id().getValue()))
                .set(STOREROOM_PRODUCTS.SP_NAME, product.name().getName())
                .set(STOREROOM_PRODUCTS.SP_STOREROOM_ID, UUID.fromString(storeroomId.getValue()))
                .set(STOREROOM_PRODUCTS.SP_CREATION, DSL.currentTimestamp())
                .set(STOREROOM_PRODUCTS.SP_MODIFICATION, DSL.currentTimestamp())
                .execute();
        LOGGER.debug("Product {} created in storeroom {}", product, storeroomId);
        break;
      case MODIFIED:
        dslContext.update(STOREROOM_PRODUCTS)
                .set(STOREROOM_PRODUCTS.SP_STOCK, product.stock().getValue())
                .set(STOREROOM_PRODUCTS.SP_MODIFICATION, DSL.currentTimestamp())
                .where(STOREROOM_PRODUCTS.SP_ID.eq(UUID.fromString(product.id().getValue())))
                .execute();
        LOGGER.debug("Product {} updated in storeroom {}", product, storeroomId);
        break;
      default:
        LOGGER.debug("Product {} unchanged in storeroom {}", product, storeroomId);
    }

  }

  @Override
  public Optional<Storeroom> findById(final StoreroomId storeroomId) {
    Optional<Storeroom> storeroom = dslContext.selectFrom(STOREROOMS)
            .where(STOREROOMS.S_ID.eq(UUID.fromString(storeroomId.getValue())))
            .fetchOptional(storeroomDataRecordMapper);

    List<Product> products = dslContext.selectFrom(STOREROOM_PRODUCTS)
            .where(STOREROOM_PRODUCTS.SP_STOREROOM_ID.eq(UUID.fromString(storeroomId.getValue())))
            .orderBy(STOREROOM_PRODUCTS.SP_NAME)
            .fetch(productsDataRecordMapper);

    return (storeroom.isPresent() ? storeroomWithProducts(storeroom.get(), products) : storeroom);
  }

  @Override
  public void update(final Storeroom storeroom) {
    LOGGER.debug("Updating {}", storeroom);
    storeroom.products().getProducts()
            .forEach(product -> save(storeroom.id(), product));
  }

  private Optional<Storeroom> storeroomWithProducts(final Storeroom storeroom, final List<Product> products) {
    return Optional.of(Storeroom.builder()
            .withName(storeroom.name())
            .withId(storeroom.id())
            .withProducts(new Products(new HashSet<>(products)))
            .build());
  }
}
