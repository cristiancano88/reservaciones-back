package reservar.vuelos.bl.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

import com.google.gson.JsonArray;

import reservar.vuelos.bl.IVueloController;
import reservar.vuelos.dao.DAOException;
import reservar.vuelos.dao.IVueloDAO;
import reservar.vuelos.dao.impl.ImplVueloDAO;

public class VueloController implements IVueloController{
    IVueloDAO vuelosDao = new ImplVueloDAO();

    @Override
    public JsonArray getList() throws SQLException, NamingException {
    	JsonArray vuelos = new JsonArray();
        try {
            vuelos = vuelosDao.obtenerVuelos();
        } catch (DAOException ex) {
            Logger.getLogger(VueloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return vuelos;
    }
}
