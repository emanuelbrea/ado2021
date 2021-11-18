import model.moduloNotificaciones.estrategias.Estrategia;
import model.postulante.*;
import model.publicacion.*;
import model.users.Empresa;
import org.junit.Test;
import service.PostulacionService;
import service.PublicacionService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.Period;

public class TestPostulante {
    /**
     * Prueba el comportamiento de la clase postulacion junto a postulante y publicacion.
     * Los postulantes podrán postularse en las búsquedas laborales activas, siempre y cuando sus perfiles cumplan
     * con los requisitos solicitados. En caso de no cumplir con los requisitos, el sistema deberá mostrarle un “cartel”
     * informativo avisándole de esta situación.
     */

    LocalDateTime vigencia = LocalDateTime.now().plusDays(30);
    Categoria categoria = Categoria.CONTABLE;

    PublicacionService publicacionService = new PublicacionService();
    PostulacionService postulacionService = new PostulacionService();
    Postulante postulante = new Postulante("emanuel", "brea",LocalDateTime.now());

    Double montoPretendido = 5000.0;
    Experiencia experiencia = Experiencia.AVANZADO;
    Empresa empresa = new Empresa("Coto","CUIT","124512","coto@gmail.com",
            "jose", "perez");
    Estrategia estrategia = Estrategia.EMAIL;

    @Test
    public void testCrearPostulante(){
        /**
         * Prueba que un postulante se puede postular a una publicacion activa, y que este se añade a la lista de
         * postulantes de la publicacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);
        assert (postulacion.getPostulante() == postulante);

    }

    @Test
    public void testCrearPostulanteSinRequisitos(){
        /**
         * Prueba que si la publicacion no tiene requistitos, el postulante se puede postular satisfactoriamente.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);
        assert (postulacion.getPostulante() == postulante);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsPrimario(){
        /**
         * Prueba que si la publicacion requiere como minimo estudios terciarios, pero el postulante tiene estudios
         * primarios, entonces no se puede crear la postulacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.PRIMARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);
        assert (postulacion == null);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsSecundario(){
        /**
         * Prueba que si la publicacion requiere como minimo estudios terciarios, pero el postulante tiene estudios
         * secundario, entonces no se puede crear la postulacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.SECUNDARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);
        assert (postulacion == null);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsTerciario(){
        /**
         * Prueba que si la publicacion requiere como minimo estudios terciarios y el postulante los tiene, entonces
         * se puede crear la postulacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.TERCIARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);
        assert (postulacion.getPostulante() == postulante);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsUniversitario(){
        /**
         * Prueba que si la publicacion requiere como minimo estudios terciarios y el postulante los supera, entonces
         * se puede crear la postulacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.UNIVERSITARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);
        assert (postulacion.getPostulante() == postulante);

    }

    @Test
    public void testMontoPedidoMayor(){
        /**
         * Prueba que si el postulante solicita un sueldo mayor al ofrecido, la postulacion no se va a crear.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",1000.0 ,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);
        assert (postulacion == null);

    }

    @Test
    public void testIdiomaNoCumplido(){
        /**
         * Prueba que si la publicacion requiere un idioma que el postulante no maneja, entonces no se puede crear
         * la postulacion.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,vigencia, categoria, empresa, estrategia);

        publicacionService.agregarRequisito( pub, Idioma.INGLES.name(),true, TipoRequisito.IDIOMA);

        postulante.addIdioma(Idioma.FRANCES);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);
        assert (postulacion == null);

    }

    @Test
    public void testIdiomaCumplido(){
        /**
         * Prueba que si el postulante cumple todos los idiomas requeridos, entonces la postulacion es creada.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,vigencia, categoria, empresa, Estrategia.WHATSAPP);

        publicacionService.agregarRequisito( pub, Idioma.INGLES.name(),true, TipoRequisito.IDIOMA);

        postulante.addIdioma(Idioma.INGLES);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);
        assert (postulacion.getPostulante() == postulante);

    }

    @Test
    public void testPublicacionInactiva(){
        /**
         * Prueba que si se cambia la vigencia de una publicacion a una fecha pasada, cambia el estado a inactiva.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,LocalDateTime.now().minus(Period.ofDays(1)), categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulacion == null);
        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);

    }

    @Test
    public void testSeleccionarPostulante(){
        /**
         * Prueba que si la empresa selecciona un postulante, entonces la publicacion cambia el estado a cerrada.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (pub.isActive());

        postulacionService.seleccionarPostulante(postulacion);

        assert (pub.isClosed());

    }

    @Test
    public void testSeleccionarPostulanteQueNoCumpleRequisitos(){
        /**
         * Prueba que no se puede seleccionar un postulante que no cumple los requisitos.
         */
        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",1000.0 ,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (pub.isActive());

        postulacionService.seleccionarPostulante(postulacion);

        assert (pub.isActive());

    }

    @Test
    public void testEmailEsEnviadoLuegoDePostularse(){
        /**
         * Prueba que se envia un mail luego de crearse una postulacion correctamente.
         */
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, estrategia);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (outputStreamCaptor.toString().trim().contains("Enviando Email a " + empresa.getEmail()));

        System.setOut(System.out);
    }

    @Test
    public void testWhatsappEsEnviadoLuegoDePostularse(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, Estrategia.WHATSAPP);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (outputStreamCaptor.toString().trim().contains("Enviando WhatsApp a " + empresa.getTelefono()));

        System.setOut(System.out);
    }

    @Test
    public void testNoSeEnviaNotificacionConPostulacionFallida(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria, empresa, Estrategia.WHATSAPP);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (outputStreamCaptor.toString().trim().equals("El candidato no cumple con los requisitos para postularse."));

        System.setOut(System.out);
    }
}
