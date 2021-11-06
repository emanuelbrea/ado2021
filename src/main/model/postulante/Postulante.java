package model.postulante;

import model.publicacion.Categoria;
import model.publicacion.Publicacion;
import model.users.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class Postulante extends User {
    private String nombre;
    private String apellido;
    private LocalDateTime fechaNacimiento;
    private List<Nacionalidad> nacioalidades;
    private List<Idioma> idiomas;
    private HashMap<Categoria, Integer> intereses;
    private List<Publicacion> favoritos;
    private List<Postulacion> postulaciones;


}
