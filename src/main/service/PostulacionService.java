package service;

import controllers.PostulacionController;
import model.moduloNotificaciones.Notificacion;
import model.moduloNotificaciones.Notificador;
import model.moduloNotificaciones.estrategias.EstrategiaDeNotificacion;
import model.moduloNotificaciones.estrategias.NotificacionPorEmail;
import model.moduloNotificaciones.estrategias.NotificacionPorWhatsApp;
import model.moduloNotificaciones.estrategias.adapters.email.AdapterEmailJavaEmail;
import model.moduloNotificaciones.estrategias.adapters.whatsapp.AdapterWhatsAppTwilio;
import model.postulante.*;
import model.publicacion.Publicacion;
import model.publicacion.Requisito;
import model.publicacion.TipoRequisito;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PostulacionService {

    PostulacionController controller ;

    public PostulacionService() {
        this.controller = new PostulacionController();
        this.controller.borrarPostulaciones();
    }

    public Postulacion crearPostulacion(Postulante postulante, Publicacion publicacion, String cv,
                                        Double montoPretendido, Experiencia experiencia) {
        if( !publicacion.isActive()){
            return null;
        }
        if (!this.cumpleRequisitos(postulante, publicacion, montoPretendido, experiencia)) {
            return null;
        }
        Postulacion postulacion = new Postulacion(postulante, publicacion, cv);
        postulante.addPostulacion(postulacion);
        publicacion.addPostulacion(postulacion);

        controller.crearPostulacion(postulacion);

        enviarNotificacion(publicacion);

        return postulacion;

    }

    public boolean cumpleRequisitos(Postulante postulante, @NotNull Publicacion publicacion, Double montoPretendido,
                                    Experiencia experiencia) {
        List<Requisito> requisitos = publicacion.getRequisitos();
        List<Requisito> requisitosExcluyentes = requisitos.stream().filter(requisito -> requisito.isExcluyente())
                .collect(Collectors.toList());

        return cumpleMontoEsperado(publicacion, montoPretendido)
                && cumpleEstudiosEsperados(postulante, requisitosExcluyentes)
                && cumpleIdiomasEsperados(postulante, requisitosExcluyentes)
                && cumpleExperienciaEsperada(requisitosExcluyentes, experiencia);

    }

    public boolean cumpleMontoEsperado(Publicacion publicacion, Double montoPretendido) {
        return montoPretendido <= publicacion.getMonto();
    }

    public boolean cumpleEstudiosEsperados(Postulante postulante, List<Requisito> requisitosExcluyentes) {

        Requisito requisitoEstudio = requisitosExcluyentes.stream()
                .filter(requisito -> requisito.getTipo() == TipoRequisito.ESTUDIO_ALCANZADO)
                .findFirst()
                .orElse(null);

        if (requisitoEstudio == null) {
            return true;
        }

        Estudio estudioAlcanzado = postulante.getEstudioAlcanzado();
        String estudioPedido = requisitoEstudio.getDescripcion();

        return estudioAlcanzado != null && estudioAlcanzado.ordinal() >= Estudio.valueOf(estudioPedido).ordinal();

    }

    public boolean cumpleIdiomasEsperados(Postulante postulante, List<Requisito> requisitosExcluyentes) {

        List<Requisito> requisitosIdiomas = requisitosExcluyentes.stream()
                .filter(requisito -> requisito.getTipo() == TipoRequisito.IDIOMA)
                .collect(Collectors.toList());

        if (requisitosIdiomas == null || requisitosIdiomas.isEmpty()) {
            return true;
        }

        List<Idioma> idiomasPostulante = postulante.getIdiomas();
        for (Requisito requisito : requisitosIdiomas) {
            if (idiomasPostulante.stream().noneMatch(idioma -> requisito.getDescripcion() == idioma.name())) {
                return false;
            }
        }
        return true;
    }

    public boolean cumpleExperienciaEsperada(List<Requisito> requisitosExcluyentes, Experiencia experiencia) {
        Requisito requisitoExperiencia = requisitosExcluyentes.stream()
                .filter(requisito -> requisito.getTipo() == TipoRequisito.EXPERIENCIA)
                .findFirst()
                .orElse(null);

        if (requisitoExperiencia == null) {
            return true;
        }

        String experienciaPedida = requisitoExperiencia.getDescripcion();

        return experiencia.ordinal() >= Experiencia.valueOf(experienciaPedida).ordinal();
    }

    public void enviarNotificacion(Publicacion publicacion){
        Notificador notificador = new Notificador();
        EstrategiaDeNotificacion notificadorWhatsApp = new NotificacionPorWhatsApp(new AdapterWhatsAppTwilio());
        EstrategiaDeNotificacion notificadorEmail = new NotificacionPorEmail(new AdapterEmailJavaEmail());
        switch(publicacion.getEstrategia()) {
            case WHATSAPP: notificador.setEstrategia(notificadorWhatsApp); break;
            case EMAIL: notificador.setEstrategia(notificadorEmail); break;
        }
        Notificacion notificacion = new Notificacion(publicacion.getTitulo(), publicacion.getEmpresa());
        notificador.enviar(notificacion);
    }

}
