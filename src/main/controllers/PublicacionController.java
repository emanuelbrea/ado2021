package controllers;

import dao.Conexion;
import model.publicacion.Categoria;
import model.publicacion.ModalidadContrato;
import model.publicacion.Publicacion;
import model.publicacion.Trabajo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PublicacionController {

    static Connection con = Conexion.getConnection();


    public boolean crearPublicacion(Publicacion publicacion){
        boolean created = false;
        try{
            String query
                    = "insert into publicacion(titulo,descripcion, contrato, trabajo, lugarTrabajo, monto, vigencia," +
                    " categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps
                    = con.prepareStatement(query);
            ps.setString(1, publicacion.getTitulo());
            ps.setString(2, publicacion.getDescripcion());
            ps.setString(3, publicacion.getContrato().name());
            ps.setString(4, publicacion.getTipoTrabajo().name());
            ps.setString(5, publicacion.getLugarTrabajo());
            ps.setDouble(6, publicacion.getMonto());
            ps.setTimestamp(7, Timestamp.valueOf(publicacion.getVigencia()));
            ps.setString(8, publicacion.getCategoria().name());
            ps.executeUpdate();
            created = true;
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }

        return created;


    }

    public List<Publicacion> getPublicaciones() {
        List<Publicacion> publicaciones = new ArrayList<>();
        try{
            String query = "select * from publicacion";
            PreparedStatement ps
                    = con.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                String titulo = resultSet.getString("titulo");
                String descripcion = resultSet.getString("descripcion");
                String contrato = resultSet.getString("contrato");
                String trabajo = resultSet.getString("trabajo");
                String lugarTrabajo = resultSet.getString("lugarTrabajo");
                Double monto = resultSet.getDouble("monto");
                LocalDateTime vigencia = resultSet.getTimestamp("vigencia").toLocalDateTime();
                String categoria = resultSet.getString("categoria");

                publicaciones.add( new Publicacion(titulo, descripcion, ModalidadContrato.valueOf(contrato),
                        Trabajo.valueOf(trabajo), lugarTrabajo,
                        monto, vigencia, Categoria.valueOf(categoria),null));

            }
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }

        return publicaciones;
    }

    public void borrarPublicaciones() {
        try{
            String query = "delete from publicacion";
            PreparedStatement ps
                    = con.prepareStatement(query);
            ps.executeUpdate();
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}
