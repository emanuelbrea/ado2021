package model.publicacion;

public class Tarea {
    private int id;
    private final String nombre;
    private final String descripcion;
    public Tarea(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
