package model.publicacion.estado;

import model.publicacion.Publicacion;

public abstract class EstadoPublicacion {

    public abstract void manejarEstado(Publicacion publicacion);

    public abstract boolean isActive();

    public abstract boolean isInactive();

    public abstract boolean isClosed();
}
