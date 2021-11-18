package model.postulante;

import dao.PostulacionDao;
import model.publicacion.Publicacion;

import java.time.LocalDateTime;

public class Postulacion {
    private int id;
    private Postulante postulante;
    private Publicacion publicacion;
    private LocalDateTime fecha;
    private String cvPath;

    private PostulacionDao postulacionDao;

    public Postulacion(Postulante postulante, Publicacion publicacion, String cvPath) {
        this.postulante = postulante;
        this.publicacion = publicacion;
        this.cvPath = cvPath;
        this.fecha = LocalDateTime.now();

        this.postulacionDao = new PostulacionDao();

        postulacionDao.crearPostulacion(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

}
