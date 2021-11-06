package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {

    private static Connection con = null;
    private static String url = "jdbc:sqlite:local_db.sqlite";

//    static
//    {
//        String url = "jdbc:sqlite:local_db.sqlite";
//        try {
//            Class.forName("org.sqlite.JDBC");
//            con = DriverManager.getConnection(url);
//        }
//        catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//

    public static Connection getConnection() {
        if( con == null){
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection(url);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
}
