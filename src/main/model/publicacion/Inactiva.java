package model.publicacion;

public class Inactiva extends EstadoPublicacion {
    public void manejarEstado(Publicacion publicacion) {

    }

    public boolean isActive() {
        return false;
    }
}
