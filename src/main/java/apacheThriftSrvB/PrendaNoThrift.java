package apacheThriftSrvB;

public class PrendaNoThrift {
  public String nombre; // required
  public TipoNoThrift tipo; // required
  public String talla; // required
  public String color; // required
  public String descripcion; // required
  public String stock; // required

  public PrendaNoThrift() {
  }

  public PrendaNoThrift(
    String nombre,
    TipoNoThrift tipo,
    String talla,
    String color,
    String descripcion,
    String stock)
  {
    this();
    this.nombre = nombre;
    this.tipo = tipo;
    this.talla = talla;
    this.color = color;
    this.descripcion = descripcion;
    this.stock = stock;
  }

  public String getNombre() {
    return this.nombre;
  }

  public PrendaNoThrift setNombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * 
   * @see TipoNoThrift
   */
  public TipoNoThrift getTipo() {
    return this.tipo;
  }

  /**
   * 
   * @see TipoNoThrift
   */
  public PrendaNoThrift setTipo(TipoNoThrift tipo) {
    this.tipo = tipo;
    return this;
  }

  public String getTalla() {
    return this.talla;
  }

  public PrendaNoThrift setTalla(String talla) {
    this.talla = talla;
    return this;
  }

  public String getColor() {
    return this.color;
  }

  public PrendaNoThrift setColor(String color) {
    this.color = color;
    return this;
  }

  public String getDescripcion() {
    return this.descripcion;
  }

  public PrendaNoThrift setDescripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  public String getStock() {
    return this.stock;
  }

  public PrendaNoThrift setStock(String stock) {
    this.stock = stock;
    return this;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PrendaThrift(");
    boolean first = true;

    sb.append("nombre:");
    if (this.nombre == null) {
      sb.append("null");
    } else {
      sb.append(this.nombre);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("tipo:");
    if (this.tipo == null) {
      sb.append("null");
    } else {
      sb.append(this.tipo);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("talla:");
    if (this.talla == null) {
      sb.append("null");
    } else {
      sb.append(this.talla);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("color:");
    if (this.color == null) {
      sb.append("null");
    } else {
      sb.append(this.color);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("descripcion:");
    if (this.descripcion == null) {
      sb.append("null");
    } else {
      sb.append(this.descripcion);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("stock:");
    sb.append(this.stock);
    first = false;
    sb.append(")");
    return sb.toString();
  }
}

