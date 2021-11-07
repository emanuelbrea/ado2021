package model.publicacion;

import java.time.LocalDateTime;

public class Activa extends EstadoPublicacion {
    public void manejarEstado(Publicacion publicacion) {
        if ( publicacion.getVigencia().isBefore(LocalDateTime.now())) {
            publicacion.cambiarEstado(new Inactiva());
        }
    }

    public boolean isActive() {
        return true;
    }
}
