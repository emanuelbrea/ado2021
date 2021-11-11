package model.moduloNotificaciones.estrategias;

import model.moduloNotificaciones.Notificacion;

public interface EstrategiaDeNotificacion {
	void enviar(Notificacion notificacion);
}
