package model.publicacion;

public abstract class EstadoPublicacion {

    public abstract void manejarEstado(Publicacion publicacion);

    public abstract boolean isActive();
}
