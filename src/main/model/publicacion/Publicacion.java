package model.publicacion;

import model.postulante.Postulacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Publicacion {
    private int id;
    private final String titulo;
    private final String descripcion;
    private final ModalidadContrato contrato;
    private final Trabajo tipoTrabajo;
    private final String lugarTrabajo;
    private final List<Requisito> requisitos;
    private final Double monto;
    private final LocalDateTime vigencia;
    private EstadoPublicacion estado = new Activa();
    private final Categoria categoria;
    private final List<Tarea> tareas;
    private final List<Postulacion> postulaciones;

    public Publicacion(String titulo, String descripcion, ModalidadContrato contrato,
                       Trabajo tipoTrabajo, String lugarTrabajo, Double monto,
                       LocalDateTime vigencia, Categoria categoria) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.tipoTrabajo = tipoTrabajo;
        this.lugarTrabajo = lugarTrabajo;
        this.requisitos = new ArrayList();
        this.monto = monto;
        this.vigencia = vigencia;

        if (vigencia.isBefore(LocalDateTime.now())) {
            this.estado = new Inactiva();
        }
        this.categoria = categoria;
        this.tareas = new ArrayList<>();
        this.postulaciones = new ArrayList<>();
    }

    public void cambiarEstado(EstadoPublicacion estado) {
        this.estado = estado;
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

    public void addRequisito(Requisito requisito) {
        this.requisitos.add(requisito);
    }

    public void addPostulacion(Postulacion postulacion) {
        this.postulaciones.add(postulacion);
    }

    public void addTarea(Tarea tarea) {
        this.tareas.add(tarea);
    }

    public boolean isActive() {
        return this.estado.isActive();
    }

}
