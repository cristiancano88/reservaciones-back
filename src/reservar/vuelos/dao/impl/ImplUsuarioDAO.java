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
import reservar.vuelos.dao.IUsuarioDAO;
import reservar.vuelos.database.Database;
import reservar.vuelos.util.Utilidades;

public class ImplUsuarioDAO implements IUsuarioDAO {

    final String OBTENER_TODOS = "SELECT id, cedula, nombre, edad, email FROM usuarios";

    private Connection conn;

    public ImplUsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    public ImplUsuarioDAO() {
    }

    @Override
    public JsonArray obtenerUsuarios() throws DAOException, SQLException, NamingException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        JsonArray arrUsuarios = new JsonArray();
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

                arrUsuarios.add(fila);
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

        return arrUsuarios;
    }
}
