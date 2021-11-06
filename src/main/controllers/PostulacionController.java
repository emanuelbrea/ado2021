package controllers;

import model.postulante.Postulacion;
import model.postulante.Postulante;
import model.publicacion.Publicacion;

public class PostulacionController {

    private Postulante postulante;


    public PostulacionController(Postulante postulante) {
        this.postulante = postulante;
    }


    public void crearPostulacion(int idPublicacion){
        // save cv as file, and get path
        String cvPath = "";
//        Publicacion publicacion = Publicacion.getPublicacion(idPublicacion);
//        Postulacion postulacion = new Postulacion(this.postulante, publicacion,cvPath);

    }



}
