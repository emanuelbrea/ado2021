package model.moduloNotificaciones.adapters.email;


import model.moduloNotificaciones.Notificacion;

public class AdapterEmailJavaEmail implements AdapterNotificadorEmail {

    public void enviarEmail(Notificacion notificacion) {
        System.out.println("Enviando Email a " +
                notificacion.getEmailDestinatario() + " por Twilio: " +
                "'" + notificacion.getMensaje() + "'"
        );

    }

}
