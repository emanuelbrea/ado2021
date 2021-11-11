package service;

import dao.PublicacionDao;
import model.moduloExportador.estrategias.exportacion.FormaDeExportacion;
import model.moduloExportador.fachada.FacadeExportador;
import model.moduloNotificaciones.estrategias.Estrategia;
import model.publicacion.*;
import model.users.Empresa;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionService {

    private final PublicacionDao controller ;

    public PublicacionService() {
        this.controller =  new PublicacionDao();
        this.controller.borrarPublicaciones();
    }

    public Publicacion crearPublicacion(String titulo, String descripcion, ModalidadContrato contrato,
                                        Trabajo tipoTrabajo, String lugarTrabajo, Double monto,
                                        LocalDateTime vigencia, Categoria categoria, Empresa empresa,
                                        Estrategia estrategia) {


        if (tipoTrabajo == Trabajo.REMOTO) {
            lugarTrabajo = "";
        }

        if (titulo.isEmpty()) {
            titulo = categoria.name() + " - " + tipoTrabajo.name() + " - " + lugarTrabajo;
        }

        Publicacion publicacion = new Publicacion(titulo, descripcion, contrato, tipoTrabajo, lugarTrabajo,
                monto, vigencia, categoria, empresa, estrategia);

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

    public void changeVigencia(@NotNull Publicacion publicacion , LocalDateTime newVigencia){
        publicacion.changeVigencia(newVigencia);
    }

    public int getCantidadPublicaciones(){
        List<Publicacion> publicaciones = controller.getPublicaciones();

        return publicaciones.size();

    }

    public String exportarPublicacionAImagen(Publicacion publicacion, FormaDeExportacion exportacion){
        return FacadeExportador.exportar(publicacion, exportacion);
    }

}
