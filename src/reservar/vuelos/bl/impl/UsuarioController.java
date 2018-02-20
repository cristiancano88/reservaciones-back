package reservar.vuelos.bl.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

import com.google.gson.JsonArray;

import reservar.vuelos.bl.IUsuarioController;
import reservar.vuelos.dao.DAOException;
import reservar.vuelos.dao.IUsuarioDAO;
import reservar.vuelos.dao.impl.ImplUsuarioDAO;

public class UsuarioController implements IUsuarioController{
    IUsuarioDAO usuarioDao = new ImplUsuarioDAO();

    @Override
    public JsonArray getList() throws SQLException, NamingException {
    	JsonArray usuarios = new JsonArray();
        try {
            usuarios = usuarioDao.obtenerUsuarios();
        } catch (DAOException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuarios;
    }   
}
