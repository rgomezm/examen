package modelo;



import java.sql.*;
import java.util.*;


public class Conexion {

    /* DATOS PARA LA CONEXION */
    /**
     * base de datos por defecto es test
     */
    private String db = "empresa";
    /**
     * usuario
     */
    private String user = "root";
    /**
     * contraseña de MySql
     */
    private String password = "8162";
    /**
     * Cadena de conexion
     */
    private String url = "jdbc:mysql://localhost:3306" + db;
    /**
     * variable para trabajar con la conexion a la base de datos
     */
    private Connection conn = null;

    /**
     * Constructor de clase
     */
    public Conexion() {
        this.url = "jdbc:mysql://localhost/" + this.db;
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            conn = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public Connection getConexion() {
        return this.conn;
    }

}
