package model.moduloNotificaciones.estrategias.adapters.whatsapp;


import model.moduloNotificaciones.Notificacion;

public class AdapterWhatsAppTwilio implements AdapterNotificadorWhatsApp {

	public void enviarWhatsApp(Notificacion notificacion) {
		System.out.println("Enviando WhatsApp a " +
		notificacion.getNroCompletoDestinatario() + " por Twilio: " +
		"'" + notificacion.getMensaje() + "'"
		);
	}

}
