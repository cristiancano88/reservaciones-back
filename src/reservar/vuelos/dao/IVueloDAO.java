package reservar.vuelos.dao;

import java.sql.SQLException;
import javax.naming.NamingException;
import com.google.gson.JsonArray;

public interface IVueloDAO {
    
	public JsonArray obtenerVuelos() throws DAOException, SQLException, NamingException;
    
    public JsonArray obtenerPorId(Long id) throws DAOException, SQLException, NamingException;
}
