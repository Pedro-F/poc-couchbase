package apacheThriftSrvB;

public enum TipoNoThrift {
  CAMISA(0),
  PANTALON(1),
  ZAPATO(2);

  private final int value;

  private TipoNoThrift(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TipoNoThrift findByValue(int value) { 
    switch (value) {
      case 0:
        return CAMISA;
      case 1:
        return PANTALON;
      case 2:
        return ZAPATO;
      default:
        return null;
    }
  }
}
