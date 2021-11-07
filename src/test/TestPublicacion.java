import model.publicacion.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Period;

import service.PublicacionService;

import static org.junit.Assert.assertFalse;

public class TestPublicacion {

    LocalDateTime vigencia = LocalDateTime.now();
    Categoria categoria = Categoria.CONTABLE;
    PublicacionService service = new PublicacionService();

    @Test
    public void testCrearPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        service.agregarRequisito( pub,"saber java",true,TipoRequisito.EXPERIENCIA);

        assert( pub.getRequisitos().size() == 1);
    }

    @Test
    public void testAgregarTarea(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        service.agregarTarea( pub,"correr reportes","muchos");

        assert( pub.getTareas().size() == 1);
    }

    @Test
    public void testDeshabilitarPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        service.deshabilitarPublicacion(pub);

        assertFalse( pub.isActive());

    }

    @Test
    public void testTrabajoRemotoSinLugar(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4,vigencia, categoria);

        assert( pub.getLugarTrabajo().isEmpty());
    }

    @Test
    public void testCrearPublicacionInactiva(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia.minus(Period.ofDays(1)), categoria);

        assertFalse( pub.isActive());
    }

}
