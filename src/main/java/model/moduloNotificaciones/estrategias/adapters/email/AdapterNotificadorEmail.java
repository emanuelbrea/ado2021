package model.moduloNotificaciones.estrategias.adapters.email;


import model.moduloNotificaciones.Notificacion;

public interface AdapterNotificadorEmail {
	void enviarEmail(Notificacion notificacion);

}
