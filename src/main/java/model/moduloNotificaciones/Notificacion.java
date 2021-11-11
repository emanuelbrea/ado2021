package model.moduloNotificaciones;

import model.users.Empresa;

public class Notificacion {
	private String emailDestinatario;
	private String mensaje;
	private String nroCompletoDestinatario;

	public Notificacion( String tituloPublicacion, Empresa empresa){
		this.emailDestinatario = empresa.getEmail();
		this.nroCompletoDestinatario = empresa.getTelefono();
		this.mensaje = generateMessage(tituloPublicacion,empresa.getContactoNombre(),
				empresa.getContactoApellido(), empresa.getRazonSocial());
	}

	private String generateMessage(String titulo, String nombre, String apellido, String empresa){
		String message = String.format("Estimado %s %s de la empresa %s. Hay una nueva postulacion en la publicacion " +
				"%s. Saludos", nombre, apellido, empresa, titulo);
		return message;
	}

	public String getEmailDestinatario() {
		return emailDestinatario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getNroCompletoDestinatario() {
		return nroCompletoDestinatario;
	}
}

