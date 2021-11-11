package model.moduloNotificaciones.estrategias;


import model.moduloNotificaciones.Notificacion;
import model.moduloNotificaciones.estrategias.adapters.whatsapp.AdapterNotificadorWhatsApp;

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
