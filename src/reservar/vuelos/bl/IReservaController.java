package reservar.vuelos.bl;

import java.sql.SQLException;
import javax.naming.NamingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import reservar.vuelos.dao.DAOException;

public interface IReservaController {
    
    public JsonArray getReservasByCedula(int cedula) throws SQLException, NamingException;  
    public JsonObject reservar(JsonObject data) throws DAOException, SQLException, NamingException;  
}
