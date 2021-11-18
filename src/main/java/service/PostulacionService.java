package service;

import dao.PostulacionDao;
import model.moduloNotificaciones.*;
import model.moduloNotificaciones.adapters.email.AdapterEmailJavaEmail;
import model.moduloNotificaciones.adapters.whatsapp.AdapterWhatsAppTwilio;
import model.postulante.*;
import model.publicacion.Publicacion;
import model.publicacion.Requisito;
import model.publicacion.TipoRequisito;

import java.util.List;
import java.util.stream.Collectors;

public class PostulacionService {

    public Postulacion crearPostulacion(Postulante postulante, Publicacion publicacion, String cv,
                                        Double montoPretendido, Experiencia experiencia) {
        if (!publicacion.isActive()) {
            System.out.println("No se pudo postular porque la publicacion no esta activa.");
            return null;
        }
        if (!this.cumpleRequisitos(postulante, publicacion, montoPretendido, experiencia)) {
            System.out.println("El candidato no cumple con los requisitos para postularse.");
            return null;
        }
        Postulacion postulacion = new Postulacion(postulante, publicacion, cv);
        postulante.addPostulacion(postulacion);
        publicacion.addPostulacion(postulacion);

        enviarNotificacion(publicacion);

        return postulacion;

    }

    private boolean cumpleRequisitos(Postulante postulante, Publicacion publicacion, Double montoPretendido,
                                     Experiencia experiencia) {
        List<Requisito> requisitos = publicacion.getRequisitos();
        List<Requisito> requisitosExcluyentes = requisitos.stream().filter(requisito -> requisito.isExcluyente())
                .collect(Collectors.toList());

        return cumpleMontoEsperado(publicacion, montoPretendido)
                && cumpleEstudiosEsperados(postulante, requisitosExcluyentes)
                && cumpleIdiomasEsperados(postulante, requisitosExcluyentes)
                && cumpleExperienciaEsperada(requisitosExcluyentes, experiencia);

    }

    private boolean cumpleMontoEsperado(Publicacion publicacion, Double montoPretendido) {
        return montoPretendido <= publicacion.getMonto();
    }

    private boolean cumpleEstudiosEsperados(Postulante postulante, List<Requisito> requisitosExcluyentes) {

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

    private boolean cumpleIdiomasEsperados(Postulante postulante, List<Requisito> requisitosExcluyentes) {

        List<Requisito> requisitosIdiomas = requisitosExcluyentes.stream()
                .filter(requisito -> requisito.getTipo() == TipoRequisito.IDIOMA)
                .collect(Collectors.toList());

        if (requisitosIdiomas == null || requisitosIdiomas.isEmpty()) {
            return true;
        }

        List<Idioma> idiomasPostulante = postulante.getIdiomas();
        for (Requisito requisito : requisitosIdiomas) {
            if (idiomasPostulante.stream().noneMatch(idioma -> requisito.getDescripcion() == idioma.getDescripcion())) {
                return false;
            }
        }
        return true;
    }

    private boolean cumpleExperienciaEsperada(List<Requisito> requisitosExcluyentes, Experiencia experiencia) {
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

    private void enviarNotificacion(Publicacion publicacion) {
        Notificador notificador = new Notificador();
        EstrategiaDeNotificacion notificadorWhatsApp = new NotificacionPorWhatsApp(new AdapterWhatsAppTwilio());
        EstrategiaDeNotificacion notificadorEmail = new NotificacionPorEmail(new AdapterEmailJavaEmail());
        switch (publicacion.getEstrategia()) {
            case WHATSAPP:
                notificador.setEstrategia(notificadorWhatsApp);
                break;
            case EMAIL:
                notificador.setEstrategia(notificadorEmail);
                break;
        }
        Notificacion notificacion = new Notificacion(publicacion.getTitulo(), publicacion.getEmpresa());
        notificador.enviar(notificacion);
    }

    public void seleccionarPostulante(Postulacion postulacion) {
        if (postulacion != null) {
            Publicacion publicacion = postulacion.getPublicacion();
            Postulante postulante = postulacion.getPostulante();
            publicacion.seleccionarPostulante(postulante);
        }
    }

}
