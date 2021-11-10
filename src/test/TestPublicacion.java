import model.moduloNotificaciones.estrategias.Estrategia;
import model.publicacion.*;
import model.users.Empresa;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Period;

import service.PublicacionService;

import static org.junit.Assert.assertFalse;

public class TestPublicacion {

    LocalDateTime vigencia = LocalDateTime.now().plusDays(30);
    Categoria categoria = Categoria.CONTABLE;
    PublicacionService service = new PublicacionService();
    Empresa empresa = new Empresa("Coto","CUIT","124512","coto@gmail.com",
            "jose", "perez");
    Estrategia estrategia = Estrategia.EMAIL;

    @Test
    public void testCrearPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria, empresa, estrategia);

        assert( service.getCantidadPublicaciones() == 1);
    }

    @Test
    public void testCrearPublicacionConRequisito(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria, empresa, estrategia);

        service.agregarRequisito( pub,"saber java",true,TipoRequisito.EXPERIENCIA);

        assert( pub.getRequisitos().size() == 1);
    }

    @Test
    public void testAgregarTarea(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria, empresa, estrategia);

        service.agregarTarea( pub,"correr reportes","muchos");

        assert( pub.getTareas().size() == 1);
    }

    @Test
    public void testTrabajoRemotoSinLugar(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4,vigencia, categoria, empresa, estrategia);

        assert( pub.getLugarTrabajo().isEmpty());
    }

    @Test
    public void testCrearPublicacionInactiva(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, LocalDateTime.now().minus(Period.ofDays(1)), categoria, empresa, estrategia);

        assert( pub.isInactive());
    }

    @Test
    public void testCrearPublicacionSinTitulo(){
        Publicacion pub =  service.crearPublicacion("","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        assertFalse( pub.getTitulo().isEmpty() );
    }

    @Test
    public void testCambiarVigencia(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        assert(pub.isActive());

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(1)));

        assert( pub.isInactive());

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(60)));

        assert( pub.isClosed());

    }

    @Test
    public void testPublicacionCerradaNoCambia(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(60)));

        assert( pub.isClosed());

        service.changeVigencia(pub, vigencia);

        assert( pub.isClosed());

    }

    @Test
    public void testReactivarPublicacion(){
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        assert(pub.isActive());

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(1)));

        assert( pub.isInactive());

        service.changeVigencia(pub, vigencia);

        assert( pub.isActive());

    }



}
