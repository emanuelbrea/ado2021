package service;
import model.publicacion.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import controllers.PublicacionController;
import org.jetbrains.annotations.NotNull;

public class PublicacionService {

    public Publicacion crearPublicacion(String titulo, String descripcion, ModalidadContrato contrato,
                                        Trabajo tipoTrabajo, String lugarTrabajo,  Double monto,
                                        LocalDateTime vigencia,  Categoria categoria) {

        Publicacion publicacion = new Publicacion(titulo, descripcion, contrato, tipoTrabajo, lugarTrabajo,
                monto, vigencia, categoria);


//        PublicacionController controller = new PublicacionController();
//        try{
//            controller.crearPublicacion(publicacion);
//        }
//        catch(SQLException e){
//
//        }
        return publicacion;
    }

    public void agregarRequisito(@NotNull Publicacion publicacion, String descripcion, boolean excluyente, TipoRequisito tipo){
        Requisito requisito = new Requisito(descripcion, excluyente, tipo);
        publicacion.addRequisito(requisito);
    }

    public void agregarTarea(@NotNull Publicacion publicacion, String nombre, String descripcion){
        Tarea tarea = new Tarea(nombre, descripcion);
        publicacion.addTarea(tarea);
    }
}
