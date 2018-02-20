package reservar.vuelos.servlets;

import com.google.gson.JsonArray;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reservar.vuelos.bl.IUsuarioController;
import reservar.vuelos.bl.impl.UsuarioController;

/**
 * Servlet implementation class users
 */
@WebServlet("/get.users")
public class Usuarios extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super();
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
    	
        IUsuarioController usuarioController = new UsuarioController();
        JsonArray usuarios = null;
        
        try {
            usuarios = usuarioController.getList();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.getWriter().write(usuarios.toString());
    }

}
