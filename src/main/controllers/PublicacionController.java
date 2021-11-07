package controllers;

import dao.Conexion;
import model.publicacion.Publicacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PublicacionController {

    static Connection con = Conexion.getConnection();


    public int crearPublicacion(Publicacion publicacion) throws SQLException {

        String query
                = "insert into publicacion(titulo,descripcion, contrato, trabajo, lugarTrabajo, monto, vigencia," +
                " estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps
                = con.prepareStatement(query);
        ps.setString(1, publicacion.getTitulo());
        ps.setString(2, publicacion.getDescripcion());
        ps.setString(3, publicacion.getContrato().name());
        ps.setString(4, publicacion.getTipoTrabajo().name());
        ps.setString(5, publicacion.getLugarTrabajo());
        ps.setDouble(6, publicacion.getMonto());
        ps.setTimestamp(7, Timestamp.valueOf(publicacion.getVigencia()));
        ps.setString(8, "ACTIVA");
        int n = ps.executeUpdate();
        return n;
    }
}
