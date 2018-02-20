package reservar.vuelos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import reservar.vuelos.dao.DAOException;
import reservar.vuelos.util.Utilidades;
import reservar.vuelos.dao.IVueloDAO;
import reservar.vuelos.database.Database;

public class ImplVueloDAO implements IVueloDAO {

    final String OBTENER_TODOS = "SELECT id, origen, destino, fecha_salida, hora_salida, fecha_llegada, hora_llegada, fecha_llegada, hora_llegada, serie_avion, precio FROM vuelos";

    private Connection conn;

    public ImplVueloDAO(Connection conn) {
        this.conn = conn;
    }

    public ImplVueloDAO() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public JsonArray obtenerVuelos() throws DAOException, SQLException, NamingException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        JsonArray arrVuelos = new JsonArray();
        conn = Database.getConnection();
        try {
            stat = conn.prepareStatement(OBTENER_TODOS);
            rs = stat.executeQuery();
            ResultSetMetaData rmd = rs.getMetaData();
            while (rs.next()) {
                JsonObject fila = new JsonObject();
                for (int i = 1; i <= rmd.getColumnCount(); i++) {
                    Utilidades.addValue(fila, rmd.getColumnLabel(i), rs.getObject(i));
                }

                arrVuelos.add(fila);
            }

        } catch (SQLException ex) {
            throw new DAOException("Eror en SQL", ex);
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }

        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException ex) {
                throw new DAOException("Error en SQL", ex);
            }
        }

        return arrVuelos;
    }

    @Override
    public JsonArray obtenerPorId(Long id) throws DAOException, SQLException, NamingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
