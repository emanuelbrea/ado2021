package service;
import model.Publicacion;

import java.sql.*;

public class PublicacionService {

    public Publicacion getPublicacion(int id) throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection con= DriverManager.getConnection(
                "jdbc:sqlite:local_db.sqlite");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM publicacion WHERE Id=" + id);

        return null;
    }
}
