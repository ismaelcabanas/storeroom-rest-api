package cabanas.garcia.ismael.shared.domain;

public abstract class Entity<E, ID> {

  /**
   *
   * @return The identity
   */
  protected abstract ID getId();

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardless of other attributes.
   */
  protected abstract boolean sameIdentityAs(E other);

}