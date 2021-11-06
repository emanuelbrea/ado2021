package service;
import model.postulante.Postulacion;
import model.publicacion.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import controllers.PublicacionController;

public class PublicacionService {

    public Publicacion crearPublicacion(String titulo, String descripcion, ModalidadContrato contrato,
                                        Trabajo tipoTrabajo, String lugarTrabajo, List<Requisito> requisitos, Double monto,
                                        LocalDateTime vigencia, EstadoPublicacion estado, Categoria categoria, List<Tarea> tareas,
                                        List<Postulacion> postulaciones) {

        Publicacion publicacion = new Publicacion(titulo, descripcion, contrato, tipoTrabajo, lugarTrabajo, requisitos,
                monto, vigencia, estado, categoria, tareas, postulaciones);


        PublicacionController controller = new PublicacionController();
        try{
            controller.crearPublicacion(publicacion);
        }
        catch(SQLException e){

        }
        return publicacion;
    }
}
