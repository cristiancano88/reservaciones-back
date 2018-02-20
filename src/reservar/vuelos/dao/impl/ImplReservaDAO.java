package reservar.vuelos.dao.impl;

import com.google.gson.JsonArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.JsonObject;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.naming.NamingException;

import reservar.vuelos.dao.DAOException;
import reservar.vuelos.dao.IReservaDAO;
import reservar.vuelos.database.Database;
import reservar.vuelos.util.Utilidades;

public class ImplReservaDAO implements IReservaDAO {

    final String INSERTAR = "INSERT INTO reservas(id_vuelo, id_usuario, costo) VALUES(?, ?, ?)";
    final String OBTENER_POR_CEDULA_USUARIO = "SELECT u.cedula, u.nombre, v.origen, v.destino, v.fecha_salida, v.hora_salida, v.fecha_llegada, v.hora_llegada, v.serie_avion\n"
            + "FROM reservas AS r INNER JOIN vuelos AS v \n"
            + "     ON r.id_vuelo = v.id\n"
            + "     INNER JOIN usuarios AS u \n"
            + "     ON u.id = r.id_usuario\n"
            + "WHERE u.cedula = ?";

    private Connection conn;
    
    @Override
    public JsonArray obtenerReservasPorCedula(int cedula) throws DAOException, SQLException, NamingException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        JsonArray arrReservas = new JsonArray();
        conn = Database.getConnection();
        try {
            stat = conn.prepareStatement(OBTENER_POR_CEDULA_USUARIO);
            stat.setInt(1, cedula);
            rs = stat.executeQuery();
            ResultSetMetaData rmd = rs.getMetaData();
            while (rs.next()) {
                JsonObject fila = new JsonObject();
                for (int i = 1; i <= rmd.getColumnCount(); i++) {
                    Utilidades.addValue(fila, rmd.getColumnLabel(i), rs.getObject(i));
                }

                arrReservas.add(fila);
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

        return arrReservas;
    }
    
    @Override
    public JsonObject reservar(JsonObject data) throws DAOException, SQLException, NamingException {
        PreparedStatement stat = null;
        ResultSet rs = null;
//        JsonArray arrReservas = new JsonArray();
        JsonObject ret = new JsonObject();
        conn = Database.getConnection();
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setInt(1, data.get("id_vuelo").getAsInt());
            stat.setInt(2, data.get("id_usuario").getAsInt());
            stat.setString(3, data.get("precio").getAsString());
            if(stat.executeUpdate() == 0) {
                throw new DAOException("Error al almacenar el registro");
            } else {
                ret.addProperty("response", "successful");
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

        return ret;
    }
    
        /*@Override
    public void insertar(String r) throws DAOException {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERTAR);
            stat.setLong(1, r.getId_vuelo());
            stat.setLong(2, r.getId_usuario());
            stat.setString(3, r.getCosto());
            stat.executeQuery();
        } catch(SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        }finally {
            if(stat != null) {
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
    }*/

    /*@Override
    public JsonObject obtenerReservasPorCedula(int cedula) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Reserva> reservas = new ArrayList<>();
        try {
            stat = conn.prepareStatement(OBTENER_POR_CEDULA_USUARIO);
            stat.setInt(1, cedula);
//            while (rs.next()) {
//                reservas.add(convertir(rs));
//            }
            
        } catch (SQLException ex) {
            throw new DAOException("Eror en SQL", ex);
        } finally {
            if(stat != null) {
                try {
                    stat.close();
                }
                catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        
        return reservas;
    }*/    
}
