package model.postulante;

import model.publicacion.Categoria;
import model.publicacion.Publicacion;
import model.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Postulante extends User {
    private final String nombre;
    private final String apellido;
    private final LocalDateTime fechaNacimiento;
    private final List<Nacionalidad> nacionalidades = new ArrayList<>();
    private final List<Idioma> idiomas = new ArrayList<>();
    private HashMap<Categoria, Integer> intereses;
    private final List<Publicacion> favoritos = new ArrayList<>();
    private final List<Postulacion> postulaciones = new ArrayList<>();
    private Estudio estudioAlcanzado;

    public Postulante(String nombre, String apellido, LocalDateTime fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }

    public void addPostulacion(Postulacion postulacion) {
        this.postulaciones.add(postulacion);
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public Estudio getEstudioAlcanzado() {
        return estudioAlcanzado;
    }

    public void setEstudioAlcanzado(Estudio estudioAlcanzado) {
        this.estudioAlcanzado = estudioAlcanzado;
    }

    public void addIdioma(Idioma idioma) {
        this.idiomas.add(idioma);
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }
}
