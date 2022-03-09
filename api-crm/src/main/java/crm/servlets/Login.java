package crm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.utils.PasswordHash;
import crm.utils.TokenJWT;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UtilisateurDao utilisateurDao;
    /**	
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    
    @Override
    public void init() {
    	this.utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String motDePasseHash;
		String login = request.getParameter("login");
		String motDePasseClair = request.getParameter("motDePasse");
		String tokenFourni = request.getHeader("Authorization");
		response.setCharacterEncoding("UTF-8");
		try {
			motDePasseHash = PasswordHash.HashPassSHA256(motDePasseClair);
			if(utilisateurDao.Identification(login, motDePasseHash)) {
				JsonObject utilisateurJson = new JsonObject();
				utilisateurJson.addProperty("login", login);
				
				
				String token = tokenFourni==null?TokenJWT.generateJWT(login, 60):tokenFourni;
				utilisateurJson.addProperty("token", token);
				
				response.setContentType("application/json");
				response.getWriter().write(utilisateurJson.toString());
			}
			else {
				response.setStatus(403);
				response.setContentType("text/plain");
				response.getWriter().write("Mot de passe ou login incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur interne " + e.getMessage());
		}
	}

}
