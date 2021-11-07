package model.publicacion;

public class Activa extends EstadoPublicacion {
    public void manejarEstado(Publicacion publicacion) {
        if (true) {
            publicacion.cambiarEstado(new Inactiva());
        }
    }

    public boolean isActive() {
        return true;
    }
}
