package service;

import controllers.PublicacionController;
import model.publicacion.*;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PublicacionService {

    private final PublicacionController controller ;

    public PublicacionService() {
        this.controller =  new PublicacionController();
        this.controller.borrarPublicaciones();
    }

    public Publicacion crearPublicacion(String titulo, String descripcion, ModalidadContrato contrato,
                                        Trabajo tipoTrabajo, String lugarTrabajo, Double monto,
                                        LocalDateTime vigencia, Categoria categoria) {


        if (tipoTrabajo == Trabajo.REMOTO) {
            lugarTrabajo = "";
        }

        if (titulo.isEmpty()) {
            titulo = categoria.name() + " - " + tipoTrabajo.name() + " - " + lugarTrabajo;
        }

        Publicacion publicacion = new Publicacion(titulo, descripcion, contrato, tipoTrabajo, lugarTrabajo,
                monto, vigencia, categoria);

        controller.crearPublicacion(publicacion);

        return publicacion;
    }

    public void agregarRequisito(@NotNull Publicacion publicacion, String descripcion, boolean excluyente, TipoRequisito tipo) {
        Requisito requisito = new Requisito(descripcion, excluyente, tipo);
        publicacion.addRequisito(requisito);
    }

    public void agregarTarea(@NotNull Publicacion publicacion, String nombre, String descripcion) {
        Tarea tarea = new Tarea(nombre, descripcion);
        publicacion.addTarea(tarea);
    }

    public void deshabilitarPublicacion(@NotNull Publicacion publicacion) {
        publicacion.cambiarEstado(new Inactiva());
    }

    public void habilitarPublicacion(@NotNull Publicacion publicacion) {
        publicacion.cambiarEstado(new Activa());
    }

    public int getCantidadPublicaciones(){
        List<Publicacion> publicaciones = controller.getPublicaciones();

        return publicaciones.size();

    }

}
