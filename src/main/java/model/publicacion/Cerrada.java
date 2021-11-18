package model.publicacion;

public class Cerrada extends EstadoPublicacion {

    @Override
    public void manejarEstado(Publicacion publicacion) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isInactive() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return true;
    }
}
