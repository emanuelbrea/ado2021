import model.publicacion.*;
import org.junit.Test;

import java.time.LocalDateTime;
import service.PublicacionService;

public class TestPublicacion {
    @Test
    public void testCrearPublicacion(){

        LocalDateTime vigencia = LocalDateTime.now();
        Categoria categoria = new Categoria("informatica", "empresa de software");

        PublicacionService service = new PublicacionService();

        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        service.agregarRequisito( pub,"saber java",true,TipoRequisito.EXPERIENCIA);

        assert( pub.getRequisitos().size() == 1);
    }

    @Test
    public void testAgregarTarea(){

        LocalDateTime vigencia = LocalDateTime.now();
        Categoria categoria = new Categoria("informatica", "empresa de software");

        PublicacionService service = new PublicacionService();

        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        service.agregarTarea( pub,"correr reportes","muchos");

        assert( pub.getTareas().size() == 1);
    }

}
