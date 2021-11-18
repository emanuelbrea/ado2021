package service;

import dao.PublicacionDao;
import model.moduloExportador.FacadeExportador;
import model.moduloExportador.estrategias.exportacion.FormaDeExportacion;
import model.moduloNotificaciones.Estrategia;
import model.publicacion.*;
import model.users.Empresa;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionService {

    private final PublicacionDao postulacionDao;

    public PublicacionService() {
        this.postulacionDao = new PublicacionDao();
        this.postulacionDao.borrarPublicaciones();
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

        postulacionDao.crearPublicacion(publicacion);

        return publicacion;
    }

    public void agregarRequisito(Publicacion publicacion, String descripcion, boolean excluyente, TipoRequisito tipo) {
        Requisito requisito = new Requisito(descripcion, excluyente, tipo);
        publicacion.addRequisito(requisito);
    }

    public void agregarTarea(Publicacion publicacion, String nombre, String descripcion) {
        Tarea tarea = new Tarea(nombre, descripcion);
        publicacion.addTarea(tarea);
    }

    public void changeVigencia(Publicacion publicacion, LocalDateTime newVigencia) {
        publicacion.changeVigencia(newVigencia);
    }

    public int getCantidadPublicaciones() {
        List<Publicacion> publicaciones = postulacionDao.getPublicaciones();

        return publicaciones.size();

    }

    public String exportarPublicacionAImagen(Publicacion publicacion, FormaDeExportacion exportacion) {
        return FacadeExportador.exportar(publicacion, exportacion);
    }

}
