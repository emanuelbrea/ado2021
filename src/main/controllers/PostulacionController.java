package controllers;

import model.postulante.Postulante;

public class PostulacionController {

    private final Postulante postulante;


    public PostulacionController(Postulante postulante) {
        this.postulante = postulante;
    }


    public void crearPostulacion(int idPublicacion) {
        // save cv as file, and get path
        String cvPath = "";
//        Publicacion publicacion = Publicacion.getPublicacion(idPublicacion);
//        Postulacion postulacion = new Postulacion(this.postulante, publicacion,cvPath);

    }


}
