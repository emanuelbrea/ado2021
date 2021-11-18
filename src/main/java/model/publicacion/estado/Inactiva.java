package model.publicacion.estado;

import model.publicacion.Publicacion;

import java.time.LocalDateTime;

public class Inactiva extends EstadoPublicacion {

    @Override
    public void manejarEstado(Publicacion publicacion) {

        if (publicacion.getPostulanteElegido() != null) {
            publicacion.cambiarEstado(new Cerrada());
        }

        int cantidadMaxDiasInactiva = publicacion.getCantidadMaxDiasInactiva();
        LocalDateTime today = LocalDateTime.now();

        if (publicacion.getVigencia().isAfter(today)) {
            publicacion.cambiarEstado(new Activa());
        } else if (publicacion.getVigencia().plusDays(cantidadMaxDiasInactiva).isBefore(today)) {
            publicacion.cambiarEstado(new Cerrada());
        }
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean isInactive() {
        return true;
    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
