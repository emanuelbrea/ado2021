package dao;

import model.postulante.Postulacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostulacionDao {

    static Connection con = Conexion.getConnection();

    public PostulacionDao(){
        this.borrarPostulaciones();
    }

    public boolean crearPostulacion(Postulacion postulacion) {
        boolean created = false;
        try {
            String query = "insert into postulacion( nombrePostulante, apellidoPostulante, tituloPublicacion) values" +
                    "(?, ?, ?)";
            PreparedStatement ps
                    = con.prepareStatement(query);
            ps.setString(1, postulacion.getPostulante().getNombre());
            ps.setString(2, postulacion.getPostulante().getApellido());
            ps.setString(3, postulacion.getPublicacion().getTitulo());
            ps.executeUpdate();
            created = true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return created;

    }

    public void borrarPostulaciones() {
        try {
            String query = "delete from postulacion";
            PreparedStatement ps
                    = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


}
