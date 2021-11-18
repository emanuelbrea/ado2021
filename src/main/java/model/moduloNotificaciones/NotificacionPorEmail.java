package model.moduloNotificaciones;


import model.moduloNotificaciones.adapters.email.AdapterNotificadorEmail;

public class NotificacionPorEmail implements EstrategiaDeNotificacion {

    private AdapterNotificadorEmail adapter;

    public void setAdapter(AdapterNotificadorEmail adapter) {
        this.adapter = adapter;
    }

    public NotificacionPorEmail(AdapterNotificadorEmail adapter) {
        super();
        this.adapter = adapter;
    }

    public void enviar(Notificacion notificacion) {
        this.adapter.enviarEmail(notificacion);
    }

}
