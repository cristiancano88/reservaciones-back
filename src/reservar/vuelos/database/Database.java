package reservar.vuelos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.NamingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Database {

    private Connection conexion = null;

    public Database() throws SQLException {
    }

    public static Connection getConnection() throws SQLException, NamingException {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost/vuelos-reservaciones";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOG.error("Error durante el uso de JDBC", e);
        } 
        
        return conn;
    }
    
    public final void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(Database.class);
}
