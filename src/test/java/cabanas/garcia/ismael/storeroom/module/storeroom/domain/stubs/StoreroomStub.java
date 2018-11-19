package cabanas.garcia.ismael.storeroom.module.storeroom.domain.stubs;

import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Product;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Products;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.Storeroom;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomId;
import cabanas.garcia.ismael.storeroom.module.storeroom.domain.StoreroomName;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class StoreroomStub {

  private StoreroomStub() {
  }

  public static Storeroom create(StoreroomId storeroomId, StoreroomName storeroomName) {
    return Storeroom.builder()
            .withId(storeroomId)
            .withName(storeroomName)
            .build();
  }

  public static Storeroom emptyStoreroom() {
    return Storeroom.builder()
            .withId(StoreroomIdStub.random())
            .withName(StoreroomNameStub.random())
            .build();
  }

  public static Storeroom createWithProducts(Product... products) {
    return Storeroom.builder()
            .withId(StoreroomIdStub.random())
            .withName(StoreroomNameStub.random())
            .withProducts(new Products(Arrays.stream(products).collect(Collectors.toSet())))
            .build();
  }
}
