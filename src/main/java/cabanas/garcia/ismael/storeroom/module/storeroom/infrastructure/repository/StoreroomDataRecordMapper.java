package cabanas.garcia.ismael.storeroom.module.storeroom.infrastructure.repository;

import cabanas.garcia.ismael.storeroom.module.product.infrastructure.repository.jooq.autogen.tables.records.StoreroomsRecord;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;
import org.jooq.RecordMapper;

public class StoreroomDataRecordMapper implements RecordMapper<StoreroomsRecord, Storeroom> {
  @Override
  public Storeroom map(StoreroomsRecord storeroomRecord) {
    return Storeroom.builder()
            .withId(new StoreroomId(storeroomRecord.getSId().toString()))
            .withName(new StoreroomName(storeroomRecord.getSName()))
            .build();
  }
}
