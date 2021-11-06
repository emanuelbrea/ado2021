package model.publicacion;

import model.postulante.Postulacion;

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
    private Double monto;
    private LocalDateTime vigencia;
    private EstadoPublicacion estado;
    private Categoria categoria;
    private List<Tarea> tareas;
    private List<Postulacion> postulaciones;

    public void cambiarEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }


    public Publicacion(String titulo, String descripcion, ModalidadContrato contrato,
                       Trabajo tipoTrabajo, String lugarTrabajo, List<Requisito> requisitos, Double monto,
                       LocalDateTime vigencia, EstadoPublicacion estado, Categoria categoria, List<Tarea> tareas,
                       List<Postulacion> postulaciones) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.tipoTrabajo = tipoTrabajo;
        this.lugarTrabajo = lugarTrabajo;
        this.requisitos = requisitos;
        this.monto = monto;
        this.vigencia = vigencia;
        this.estado = estado;
        this.categoria = categoria;
        this.tareas = tareas;
        this.postulaciones = postulaciones;

    }


    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ModalidadContrato getContrato() {
        return contrato;
    }

    public Trabajo getTipoTrabajo() {
        return tipoTrabajo;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public List<Requisito> getRequisitos() {
        return requisitos;
    }

    public Double getMonto() {
        return monto;
    }

    public LocalDateTime getVigencia() {
        return vigencia;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }
}
