package model;

import java.time.LocalDateTime;

public class Postulacion {
    private int id ;
    private Postulante postulante;
    private Publicacion publicacion;
    private LocalDateTime fecha;
    private String cvPath;

    public Postulacion(Postulante postulante, Publicacion publicacion, String cvPath) {
        this.postulante = postulante;
        this.publicacion = publicacion;
        this.cvPath = cvPath;
        this.fecha = LocalDateTime.now();


        PostulacionService postulacionService = new PostulacionService();
        this.id = postulacionService.savePostulacion(postulante, publicacion,fecha, cvPath);
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

}
