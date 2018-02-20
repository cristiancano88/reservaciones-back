package reservar.vuelos.dao;

import java.sql.SQLException;
import javax.naming.NamingException;
import com.google.gson.JsonArray;

public interface IUsuarioDAO {
	public JsonArray obtenerUsuarios() throws DAOException, SQLException, NamingException;
}
