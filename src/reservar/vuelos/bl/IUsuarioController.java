package reservar.vuelos.bl;

import java.sql.SQLException;
import javax.naming.NamingException;

import com.google.gson.JsonArray;

public interface IUsuarioController {
    
    public JsonArray getList() throws SQLException, NamingException;  
}
