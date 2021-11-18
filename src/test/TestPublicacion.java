import model.moduloExportador.estrategias.exportacion.FormaDeExportacion;
import model.moduloNotificaciones.estrategias.Estrategia;
import model.publicacion.*;
import model.users.Empresa;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Period;

import service.PublicacionService;

public class TestPublicacion {

    LocalDateTime vigencia = LocalDateTime.now().plusDays(30);
    Categoria categoria = Categoria.CONTABLE;
    PublicacionService service = new PublicacionService();
    Empresa empresa = new Empresa("Coto","CUIT","124512","coto@gmail.com",
            "jose", "perez");
    Estrategia estrategia = Estrategia.EMAIL;

    @Test
    public void testCrearPublicacion(){
        /**
         * Pueba que se puede crear una publicacion con los datos obligatorios.
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4,vigencia, categoria, empresa, estrategia);

        assert( service.getCantidadPublicaciones() == 1);
    }

    @Test
    public void testCrearPublicacionConRequisito(){
        /**
         * Prueba que se puede agregar requisitos a una publicacion.
         */
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
        /**
         * Prueba que solamente se deberá completar si el tipo de trabajo es presencial y no se deberá
         * especificar la dirección exacta, sino una referencia
         */
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
        /**
         * Si está vacío, el sistema deberá generar un título de forma
         * automática tomando en cuenta la Categoría, el Tipo de trabajo y Lugar de trabajo.
         */
        Publicacion pub =  service.crearPublicacion("","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",950.4, vigencia, categoria, empresa, estrategia);

        assert (pub.getTitulo().equals(categoria.name() + " - " + Trabajo.PRESENCIAL + " - " + "caba"));
    }

    @Test
    public void testCambiarVigencia(){
        /**
         * Cada publicación deberá tener un periodo de vigencia durante el cual estará abierta para la recepción de
         * postulantes. Fuera de este periodo, deberá figurar por N semanas como “Búsqueda cerrada”
         */
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
        /**
         * Prueba que una publicacion cerrada (no inactiva) no puede reabrirse.
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(60)));

        assert( pub.isClosed());

        service.changeVigencia(pub, vigencia);

        assert( pub.isClosed());

    }

    @Test
    public void testReactivarPublicacion(){
        /**
         * Cabe destacar que, si el puesto ofrecido por una publicación no fue cubierto, la empresa interesada
         * podrá volverla a reabrir
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        assert(pub.isActive());

        service.changeVigencia(pub, LocalDateTime.now().minus(Period.ofDays(1)));

        assert( pub.isInactive());

        service.changeVigencia(pub, vigencia);

        assert( pub.isActive());

    }

    @Test
    public void testExportarPublicacionJPG(){
        /**
         * El sistema deberá generar una imagen de una publicación de oferta laboral, en distintos formatos
         * (jpg, png, svg, entre otras), que contenga, en todos los casos, un encabezado, un cuerpo y un pie.
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        String exportacion = service.exportarPublicacionAImagen(pub, FormaDeExportacion.JPG);

        assert (exportacion.contains(pub.getTitulo()));
    }

    @Test
    public void testExportarPublicacionPNG(){
        /**
         * Prueba que se puede exportar a png una publicacion.
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        String exportacion = service.exportarPublicacionAImagen(pub, FormaDeExportacion.PNG);

        assert (exportacion.contains(pub.getTitulo()));
    }

    @Test
    public void testExportarPublicacionSVG(){
        /**
         * Prueba que se puede exportar a svg una publicacion.
         */
        Publicacion pub =  service.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.REMOTO, "caba",950.4, vigencia, categoria, empresa, estrategia);

        String exportacion = service.exportarPublicacionAImagen(pub, FormaDeExportacion.SVG);

        assert (exportacion.contains(pub.getTitulo()));
    }




}
