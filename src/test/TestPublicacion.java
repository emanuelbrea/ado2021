import model.publicacion.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Period;

import service.PublicacionService;

import static org.junit.Assert.assertFalse;

public class TestPublicacion {

    LocalDateTime vigencia = LocalDateTime.now().plusDays(30);
    Categoria categoria = Categoria.CONTABLE;
    PublicacionService service = new PublicacionService();

    @Test
    public void testCrearPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria);

        assert( service.getCantidadPublicaciones() == 1);
    }

    @Test
    public void testCrearPublicacionConRequisito(){
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
    public void testHabilitarPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,LocalDateTime.now().minus(Period.ofDays(1)), categoria);

        assertFalse( pub.isActive());

        service.habilitarPublicacion(pub);

        assert( pub.isActive());
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
                Trabajo.REMOTO, "caba",950.4, LocalDateTime.now().minus(Period.ofDays(1)), categoria);

        assertFalse( pub.isActive());
    }

    @Test
    public void testCrearPublicacionSinTitulo(){
        Publicacion pub =  service.crearPublicacion("","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria);

        assertFalse( pub.getTitulo().isEmpty() );
    }



}
