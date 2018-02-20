package reservar.vuelos.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import javax.naming.NamingException;

public interface IReservaDAO {
//    public void insertar(Reserva a) throws DAOException;
    
    public JsonArray obtenerReservasPorCedula(int cedula) throws DAOException, SQLException, NamingException;
    public JsonObject reservar(JsonObject data) throws DAOException, SQLException, NamingException;
}
