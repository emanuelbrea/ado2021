import model.postulante.Postulacion;
import model.publicacion.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import service.PublicacionService;

public class TestPublicacion {
    @Test
    public void testCrearPublicacion(){

        Requisito requisito = new Requisito("saber java",true,TipoRequisito.EXPERIENCIA );
        List<Requisito> requisitos = new ArrayList<>();
        requisitos.add(requisito);
        LocalDateTime vigencia = LocalDateTime.now();
        Categoria categoria = new Categoria("informatica", "empresa de software");
        Tarea tarea = new Tarea("correr reportes", "muchos");
        List<Tarea> tareas = new ArrayList<>();
        tareas.add(tarea);
        List<Postulacion> postulaciones = new ArrayList<>();
        EstadoPublicacion estadoPublicacion = new Activa();

        PublicacionService service = new PublicacionService();

        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",requisitos, 950.4,vigencia, estadoPublicacion ,categoria,
                tareas, postulaciones);


        assert( pub.getTipoTrabajo() == Trabajo.PRESENCIAL);
    }

}
