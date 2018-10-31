package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

public interface StoreroomRepository {
  void save(Storeroom storeroom);

  void save(Product product);
}
