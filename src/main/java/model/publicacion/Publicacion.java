package model.publicacion;

import dao.PublicacionDao;
import model.moduloNotificaciones.Estrategia;
import model.postulante.Postulacion;
import model.postulante.Postulante;
import model.publicacion.estado.Activa;
import model.publicacion.estado.EstadoPublicacion;
import model.publicacion.estado.Inactiva;
import model.users.Empresa;

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
    private LocalDateTime vigencia;
    private EstadoPublicacion estado = new Activa();
    private final Categoria categoria;
    private final List<Tarea> tareas;
    private final List<Postulacion> postulaciones;
    private final int cantidadMaxDiasInactiva = 28;
    private final Empresa empresa;
    private final Estrategia estrategia;
    private Postulante postulanteElegido;

    private final PublicacionDao publicacionDao;

    public Publicacion(String titulo, String descripcion, ModalidadContrato contrato,
                       Trabajo tipoTrabajo, String lugarTrabajo, Double monto,
                       LocalDateTime vigencia, Categoria categoria, Empresa empresa, Estrategia estrategia) {

        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contrato = contrato;
        this.tipoTrabajo = tipoTrabajo;
        this.lugarTrabajo = lugarTrabajo;
        this.requisitos = new ArrayList<>();
        this.monto = monto;
        this.vigencia = vigencia;

        if (vigencia.isBefore(LocalDateTime.now())) {
            this.estado = new Inactiva();
        }
        this.categoria = categoria;
        this.tareas = new ArrayList<>();
        this.postulaciones = new ArrayList<>();
        this.empresa = empresa;
        this.estrategia = estrategia;
        this.postulanteElegido = null;
        this.publicacionDao = new PublicacionDao();

    }

    public void crearPublicacion(){
        this.publicacionDao.borrarPublicaciones();
        this.publicacionDao.crearPublicacion(this);
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

    public void manejarEstado() {
        this.estado.manejarEstado(this);
    }

    public boolean isActive() {
        this.manejarEstado();
        return this.estado.isActive();
    }

    public boolean isInactive() {
        this.manejarEstado();
        return this.estado.isInactive();
    }

    public boolean isClosed() {
        this.manejarEstado();
        return this.estado.isClosed();
    }

    public int getCantidadMaxDiasInactiva() {
        return this.cantidadMaxDiasInactiva;
    }

    public void changeVigencia(LocalDateTime vigencia) {
        this.vigencia = vigencia;
        this.manejarEstado();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Estrategia getEstrategia() {
        return estrategia;
    }

    public void seleccionarPostulante(Postulante postulante) {
        if (this.postulaciones.stream().anyMatch(postulacion -> postulacion.getPostulante() == postulante)) {
            this.postulanteElegido = postulante;
            this.manejarEstado();
        }
    }

    public int getCantidadPublicaciones() {

        List<Publicacion> publicaciones = publicacionDao.getPublicaciones();

        return publicaciones.size();

    }

    public Postulante getPostulanteElegido() {
        return postulanteElegido;
    }
}
