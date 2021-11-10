package model.users;

public class Empresa extends User {
    private String razonSocial;
    private String idTributario;
    private String telefono;
    private String email;
    private String contactoNombre;
    private String contactoApellido;

    public Empresa(String razonSocial, String idTributario, String telefono, String email, String contactoNombre, String contactoApellido) {
        this.razonSocial = razonSocial;
        this.idTributario = idTributario;
        this.telefono = telefono;
        this.email = email;
        this.contactoNombre = contactoNombre;
        this.contactoApellido = contactoApellido;
    }

    public String getRazonSocial() {
        return razonSocial;
    }
}
