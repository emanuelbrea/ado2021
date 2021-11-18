package model.moduloNotificaciones;


import model.moduloNotificaciones.adapters.whatsapp.AdapterNotificadorWhatsApp;

public class NotificacionPorWhatsApp implements EstrategiaDeNotificacion {

    private AdapterNotificadorWhatsApp adapter;

    public void setAdapter(AdapterNotificadorWhatsApp adapter) {
        this.adapter = adapter;
    }

    public NotificacionPorWhatsApp(AdapterNotificadorWhatsApp adapter) {
        super();
        this.adapter = adapter;
    }

    public void enviar(Notificacion notificacion) {
        this.adapter.enviarWhatsApp(notificacion);
    }
}
