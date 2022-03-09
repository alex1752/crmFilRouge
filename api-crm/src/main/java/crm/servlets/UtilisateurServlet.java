package crm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import crm.dao.DaoException;
import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.form.VerificationUtilisateur;
import crm.model.Utilisateur;
import crm.utils.DataHandler;
import crm.utils.PasswordHash;


/**
 * Servlet implementation class CRUD_Utilisateur
 */
@WebServlet("/utilisateur")
public class UtilisateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	
	private static final String CHAMP_ID_UTILISATEUR = "id";
	private static final String CHAMP_LOGIN = "login";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_MOTDEPASSE = "motDePasse";
	
    @Override
    public void init() throws ServletException {
        this.utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		JsonArray jsonUtilisateur = new JsonArray();
		if(request.getParameter("id")!=null) { //Details utilisateur
			Utilisateur utilisateurDetail = null;
			try {
				int idUtilisateur = Integer.parseInt(request.getParameter("id"));
				utilisateurDetail = utilisateurDao.trouver(idUtilisateur);
				jsonUtilisateur.add(JsonParser.parseString(new Gson().toJson(utilisateurDetail)).getAsJsonObject());
//				jsonUtilisateur.add(1);
				response.setContentType("application/json");
				response.getWriter().write(jsonUtilisateur.toString());
			}
			catch (DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + de.getMessage());
			}
			catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				response.setStatus(400);
				response.setContentType("text/plain");
				response.getWriter().write("Format de paramètre incorrect");
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + e.getMessage());
			}
		}
		else {//Liste Utilisateurs
			try {
				jsonUtilisateur = JsonParser.parseString(new Gson().toJson(utilisateurDao.lister())).getAsJsonArray();
//				int size = utilisateurDao.lister().size();
//				jsonUtilisateur.add(size);
				response.setContentType("application/json");
				response.getWriter().write(jsonUtilisateur.toString());
			}
			catch (DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + de.getMessage());
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + e.getMessage());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String ligne = null;
		BufferedReader reader = request.getReader();
		String erreur = "", login = null, motDePasse = null, email = null;
		
		while((ligne=reader.readLine()) != null) {
			buffer.append(ligne);
		}
		
		
		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			login = DataHandler.getNullOrString( data, CHAMP_LOGIN );
	        motDePasse = DataHandler.getNullOrString( data, CHAMP_MOTDEPASSE );
	        email = DataHandler.getNullOrString( data, CHAMP_EMAIL );
	        
	        erreur = VerificationUtilisateur.verificationCreationUtilisateur(login, motDePasse, email);
		}
		catch (JsonSyntaxException jse) {
			jse.printStackTrace();
			response.setStatus(400);
			erreur = "Format du json incorrect\n";
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur" + e.getMessage());
		}
		
		if(!erreur.equals("")) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(erreur);
		}
		else {
			
			try {
				String motDePasseHashe = PasswordHash.HashPassSHA256(motDePasse);
				Utilisateur nouvelUtilisateur = new Utilisateur(login, motDePasseHashe, email);	
				utilisateurDao.creer(nouvelUtilisateur);
				String jsonNouvelUtilisateur = new Gson().toJson(nouvelUtilisateur);
				response.setContentType("application/json");
				response.getWriter().write(jsonNouvelUtilisateur);
			}
			catch(DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur : "+de.getMessage());
			}
			catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + e.getMessage());
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		try {
			int idUtilisateur = Integer.parseInt(request.getParameter("id"));
			utilisateurDao.supprimer(idUtilisateur);
			response.setContentType("text/plain");
			response.getWriter().write("L'utilisteur a été supprimé");
		}
		catch (DaoException de) {
			de.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur" + de.getMessage());
		}
		catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write("Format de paramètre incorrect");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur" + e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String ligne = null;
		BufferedReader reader = request.getReader();
		String erreur = "", idUtilisateur = null, login = null, motDePasse = null, email = null;
		
		
		while((ligne=reader.readLine()) != null) {
			buffer.append(ligne);
		}
		
		
		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			idUtilisateur = DataHandler.getNullOrString(data, CHAMP_ID_UTILISATEUR);
			login = DataHandler.getNullOrString( data, CHAMP_LOGIN );
	        motDePasse = DataHandler.getNullOrString( data, CHAMP_MOTDEPASSE );
	        email = DataHandler.getNullOrString( data, CHAMP_EMAIL );
	        
	        
	        erreur = VerificationUtilisateur.verificationModificationUtilisateur(idUtilisateur, login, motDePasse, email);
		}
		catch (JsonSyntaxException jse) {
			jse.printStackTrace();
			response.setStatus(400);
			erreur = "Format du json incorrect\n";
		}
		
		if(!erreur.equals("")) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(erreur);
		}
		else {
			Utilisateur modifUtilisateur = null;
			
			try {
				modifUtilisateur = utilisateurDao.trouver(Integer.parseInt(idUtilisateur));
			}catch(DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Utilisateur introuvable");
			}
			catch(NumberFormatException nfe) {
				nfe.printStackTrace();
				response.setStatus(400);
				response.setContentType("text/plain");
				response.getWriter().write("Format idUtilisateur invalide");
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur" + e.getMessage());
			}
			if(modifUtilisateur != null) {
				try {
					
					modifUtilisateur.setLogin(login == null?modifUtilisateur.getLogin():login);
					
					modifUtilisateur.setMotDePasse(motDePasse == null?modifUtilisateur.getMotDePasse():PasswordHash.HashPassSHA256(motDePasse));
					
					modifUtilisateur.setEmail(email == null?modifUtilisateur.getEmail():email);
					
					utilisateurDao.update(modifUtilisateur);
					String jsonModifUtilisateur = new Gson().toJson(modifUtilisateur);
					response.setContentType("application/json");
					response.getWriter().write(jsonModifUtilisateur);
				}
				catch(DaoException de) {
					de.printStackTrace();
					response.setStatus(500);
					response.setContentType("text/plain");
					response.getWriter().write("Erreur : "+de.getMessage());
				}
				catch (Exception e) {
					e.printStackTrace();
					response.setStatus(500);
					response.setContentType("text/plain");
					response.getWriter().write("Erreur" + e.getMessage());
				}
			}else {
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur : utilisateur introuvable");
			}
		}
	}
}
