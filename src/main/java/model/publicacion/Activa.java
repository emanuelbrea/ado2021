package model.publicacion;

import java.time.LocalDateTime;

public class Activa extends EstadoPublicacion {

    @Override
    public void manejarEstado(Publicacion publicacion) {
        if (publicacion.getPostulanteElegido() != null) {
            publicacion.cambiarEstado(new Cerrada());
        } else if (publicacion.getVigencia().isBefore(LocalDateTime.now())) {
            publicacion.cambiarEstado(new Inactiva());
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public boolean isInactive() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
