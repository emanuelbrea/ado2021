package model.postulante;

public class Idioma {
    private String codigo;
    private String descripcion;

    public Idioma(String codigo, String descripcion){
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
}
