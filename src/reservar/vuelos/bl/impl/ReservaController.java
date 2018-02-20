package reservar.vuelos.bl.impl;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import reservar.vuelos.bl.IReservaController;
import reservar.vuelos.dao.DAOException;
import reservar.vuelos.dao.IReservaDAO;
import reservar.vuelos.dao.impl.ImplReservaDAO;

public class ReservaController implements IReservaController{
    IReservaDAO reservaDao = new ImplReservaDAO();

    @Override
    public JsonArray getReservasByCedula(int cedula) throws SQLException, NamingException {
        JsonArray reservas = new JsonArray();
        try {
            reservas = reservaDao.obtenerReservasPorCedula(cedula);
        } catch (DAOException ex) {
            Logger.getLogger(ReservaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return reservas;
    }

    @Override
    public JsonObject reservar(JsonObject data) throws DAOException, SQLException, NamingException {
        return reservaDao.reservar(data);
    }
}
