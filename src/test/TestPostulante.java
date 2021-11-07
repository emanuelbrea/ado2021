import model.postulante.*;
import model.publicacion.*;
import org.junit.Test;
import service.PostulacionService;
import service.PublicacionService;

import java.time.LocalDateTime;

public class TestPostulante {

    LocalDateTime vigencia = LocalDateTime.now();
    Categoria categoria = Categoria.CONTABLE;

    PublicacionService publicacionService = new PublicacionService();
    PostulacionService postulacionService = new PostulacionService();
    Postulante postulante = new Postulante("emanuel", "brea",LocalDateTime.now());

    Double montoPretendido = 5000.0;
    Experiencia experiencia = Experiencia.AVANZADO;

    @Test
    public void testCrearPostulante(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }

    @Test
    public void testCrearPostulanteSinRequisitos(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsPrimario(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.PRIMARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsSecundario(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.SECUNDARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsTerciario(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.TERCIARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }

    @Test
    public void testRequisitoEstudioTerciarioVsUniversitario(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Estudio.TERCIARIO.name(),true, TipoRequisito.ESTUDIO_ALCANZADO);

        postulante.setEstudioAlcanzado(Estudio.UNIVERSITARIO);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }

    @Test
    public void testSinRequisitoEstudio(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0,vigencia, categoria);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }

    @Test
    public void testMontoPedidoMayor(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",1000.0 ,vigencia, categoria);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);

    }

    @Test
    public void testIdiomaNoCumplido(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Idioma.INGLES.name(),true, TipoRequisito.IDIOMA);

        postulante.addIdioma(Idioma.FRANCES);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 0);
        assert (pub.getPostulaciones().size() == 0);

    }

    @Test
    public void testIdiomaCumplido(){

        Publicacion pub =  publicacionService.crearPublicacion("titulo","desc", ModalidadContrato.FULL_TIME,
                Trabajo.PRESENCIAL, "caba",10000.0 ,vigencia, categoria);

        publicacionService.agregarRequisito( pub, Idioma.INGLES.name(),true, TipoRequisito.IDIOMA);

        postulante.addIdioma(Idioma.INGLES);

        Postulacion postulacion = postulacionService.crearPostulacion(postulante, pub, "cv", montoPretendido, experiencia);

        assert (postulante.getPostulaciones().size() == 1);
        assert (pub.getPostulaciones().size() == 1);

    }
}