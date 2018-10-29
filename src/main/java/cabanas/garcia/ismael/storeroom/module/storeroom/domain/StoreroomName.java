package cabanas.garcia.ismael.storeroom.module.storeroom.domain;

import cabanas.garcia.ismael.shared.domain.ValueObject;

public final class StoreroomName extends ValueObject<StoreroomName> {
  private final String name;

  public StoreroomName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  protected boolean sameValueAs(StoreroomName other) {
    return false;
  }
}