package model;

import service.PublicacionService;

import java.time.LocalDateTime;
import java.util.List;

public class Publicacion {
    private int id;
    private String titulo;
    private String descripcion;
    private ModalidadContrato contrato;
    private Trabajo tipoTrabajo;
    private String lugarTrabajo;
    private List<Requisito> requisitos;
    private Float monto;
    private LocalDateTime vigencia;
    private EstadoPublicacion estado;
    private Categoria categoria;
    private List<Tarea> tareas;
    private List<Postulacion> postulaciones;

    public static Publicacion getPublicacion(int idPublicacion) {
        PublicacionService service = new PublicacionService();
        return service.getPublicacion(idPublicacion);
    }

    public void cambiarEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }
}
