package reservar.vuelos.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
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
import reservar.vuelos.dao.DAOException;
import reservar.vuelos.util.Utilidades;

/**
 * Servlet implementation class Reservar
 */
@WebServlet("/post.reservar")
public class Reservar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reservar() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=UTF-8");
        response.setHeader("application", "reservaciones");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("content-type", "application/json;charset=UTF-8");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//      response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//      response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
//      response.addHeader("Access-Control-Allow-Credentials", "true");
//      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        Utilidades.required(request, response, "data");
        JsonObject data = (JsonObject) new JsonParser().parse(request.getParameter("data"));
        IReservaController reservaController = new ReservaController();
        JsonObject ret = null;
        try {
            ret = reservaController.reservar(data);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(GetReservasByCedula.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(Reservar.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.getWriter().write(ret.toString());
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=UTF-8");
        response.setHeader("application", "reservaciones");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("content-type", "application/json;charset=UTF-8");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");
//      response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//      response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
//      response.addHeader("Access-Control-Allow-Credentials", "true");
//      response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");


        Utilidades.required(request, response, "data");
        JsonObject data = (JsonObject) new JsonParser().parse(request.getParameter("data"));
        IReservaController reservaController = new ReservaController();
        JsonObject ret = null;
        try {
            ret = reservaController.reservar(data);
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(GetReservasByCedula.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DAOException ex) {
            Logger.getLogger(Reservar.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.getWriter().write(ret.toString());
    }

    public static void setCorsHeaders(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Pragma");

//        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
//        response.addHeader("Access-Control-Allow-Headers","origin, content-type, accept, authorization");
//        response.addHeader("Access-Control-Allow-Credentials", "true");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }

}
