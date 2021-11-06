package model.publicacion;

public class Requisito {
    private int id;
    private String descripcion;
    private boolean excluyente;
    private TipoRequisito tipo;

    public Requisito(String descripcion, boolean excluyente, TipoRequisito tipo) {
        this.descripcion = descripcion;
        this.excluyente = excluyente;
        this.tipo = tipo;
    }
}
