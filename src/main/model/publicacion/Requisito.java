package model.publicacion;

public class Requisito {
    private int id;
    private final String descripcion;
    private final boolean excluyente;
    private final TipoRequisito tipo;

    public Requisito(String descripcion, boolean excluyente, TipoRequisito tipo) {
        this.descripcion = descripcion;
        this.excluyente = excluyente;
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isExcluyente() {
        return excluyente;
    }

    public TipoRequisito getTipo() {
        return tipo;
    }
}
