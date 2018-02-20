package reservar.vuelos.servlets;

import com.google.gson.JsonArray;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservar.vuelos.bl.IReservaController;
import reservar.vuelos.bl.impl.ReservaController;
import reservar.vuelos.util.Utilidades;

/**
 * Servlet implementation class GetReservasByCedula
 */
@WebServlet("/get.reservasByCedula")
public class GetReservasByCedula extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReservasByCedula() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
      response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
      response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
      response.addHeader("Access-Control-Allow-Credentials", "true");
      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    	
        Utilidades.required(request, response, "cedula");
        int cedula = Integer.parseInt(request.getParameter("cedula"));
        IReservaController reservaController = new ReservaController();
        JsonArray reservas = null;

        try {
            reservas = reservaController.getReservasByCedula(cedula);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(GetReservasByCedula.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.getWriter().write(reservas.toString());

    }
}
