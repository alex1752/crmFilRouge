package crm.servlets;

import java.io.BufferedReader;
import java.io.IOException;

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

import crm.dao.ClientDao;
import crm.dao.DaoException;
import crm.dao.DaoFactory;
import crm.form.VerificationClient;
import crm.model.Client;
import crm.utils.DataHandler;

/**
 * Servlet implementation class CRUD_Client
 */
@WebServlet("/client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ClientDao clientDao;
	
	private static final String CHAMP_ID_CLIENT = "id";
	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_ENTREPRISE = "entreprise";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_TELEPHONE = "telephone";
	private static final String CHAMP_MOBILE = "mobile";
	private static final String CHAMP_NOTES = "notes";
	private static final String CHAMP_ACTIF = "actif";
	private static final String CHAMP_RECHERCHE = "search";
	
	
	/*
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() {
    	this.clientDao = DaoFactory.getInstance().getClientDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try {
			String idClient = request.getParameter( CHAMP_ID_CLIENT );
			JsonArray json = new JsonArray();
			String search  = request.getParameter( CHAMP_RECHERCHE );
			int size = 0;
			if(idClient != null) {
				int id = Integer.parseInt( idClient );
				json.add(JsonParser.parseString(new Gson().toJson(clientDao.trouver(id))).getAsJsonObject());
//				json.add(1);
			}
			else if(search != null) {
				json = JsonParser.parseString(new Gson().toJson(clientDao.rechercherParNomEtPrenom(search))).getAsJsonArray();
//				size = clientDao.rechercherParNomEtPrenom(search).size();
//				json.add(size);
			}
			else {
				json = JsonParser.parseString(new Gson().toJson(clientDao.lister())).getAsJsonArray();
//				size = clientDao.lister().size();
//				json.add(size);
			}
			response.setContentType("application/json");
			response.getWriter().write(json.toString());
		}catch(DaoException de) {
        	response.setStatus(500);
        	response.setContentType("text/plain");
        	response.getWriter().write("Erreur DAO");
            de.printStackTrace();
		} catch ( NumberFormatException nfe) {
            nfe.printStackTrace();
    		response.setStatus(400); //Bad Request
    		response.setContentType("text/plain");
    		response.getWriter().write("Erreur : Le format du paramètre n'est pas bon...");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur interne");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		String erreur = "", nom = null, prenom = null, entreprise = null, email = null, telephone = null, mobile = null, notes = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			nom = DataHandler.getNullOrString(data, CHAMP_NOM);
			prenom = DataHandler.getNullOrString(data, CHAMP_PRENOM);
			entreprise = DataHandler.getNullOrString(data, CHAMP_ENTREPRISE);
			email = DataHandler.getNullOrString(data, CHAMP_EMAIL);
			telephone = DataHandler.getNullOrString(data, CHAMP_TELEPHONE);
			mobile = DataHandler.getNullOrString(data, CHAMP_MOBILE);
			notes = DataHandler.getNullOrString(data, CHAMP_NOTES);
			
			erreur = VerificationClient.verificationCreationClient(nom, prenom, entreprise, email, telephone, mobile, notes);
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
			response.getWriter().write("Erreur interne");
		}
		
		if(!erreur.equals("")) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(erreur);
		}
		else {
			try {
				Client nouveauClient = new Client(nom, prenom, entreprise, email, telephone, mobile, true, notes);
				clientDao.creer(nouveauClient);
				String jsonClient = new Gson().toJson(nouveauClient);
	    		response.setContentType("application/json");
	    		response.setContentType("text/plain");
	        	response.getWriter().write(jsonClient);
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
				response.getWriter().write("Erreur interne");
			}
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		try {
			String idClient = request.getParameter( CHAMP_ID_CLIENT );
			if(idClient != null) {
				int id = Integer.parseInt( idClient );
				clientDao.supprimer(id);
				response.setContentType("application/json");
				response.getWriter().write("Suppression client effectuée");
			}else {
				response.setStatus(400);
				response.setContentType("text/plain");
				response.getWriter().write("Paramètre incorrect");
			}
			
			
		}catch(DaoException e) {
        	response.setStatus(500);
        	response.setContentType("text/plain");
        	response.getWriter().write("Erreur DAO");
            e.printStackTrace();
		} catch ( NumberFormatException nfe) {
            nfe.printStackTrace();
    		response.setStatus(400); //Bad Request
    		response.setContentType("text/plain");
    		response.getWriter().write("Erreur : Le format du paramètre n'est pas bon...");
		}
		catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write("Erreur interne");
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		String erreur = "", idClient = null, nom = null, prenom = null, entreprise = null, email = null, telephone = null, mobile = null, actif = null, notes = null;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			idClient = DataHandler.getNullOrString(data, CHAMP_ID_CLIENT);
			nom = DataHandler.getNullOrString(data, CHAMP_NOM);
			prenom = DataHandler.getNullOrString(data, CHAMP_PRENOM);
			entreprise = DataHandler.getNullOrString(data, CHAMP_ENTREPRISE);
			email = DataHandler.getNullOrString(data, CHAMP_EMAIL);
			telephone = DataHandler.getNullOrString(data, CHAMP_TELEPHONE);
			mobile = DataHandler.getNullOrString(data, CHAMP_MOBILE);
			actif = DataHandler.getNullOrString(data, CHAMP_ACTIF);
			notes = DataHandler.getNullOrString(data, CHAMP_NOTES);
			
			erreur = VerificationClient.verificationModificationClient(idClient, nom, prenom, entreprise, email, telephone, mobile, actif, notes);
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
			response.getWriter().write("Erreur interne");
		}
		
		if(!erreur.equals("")) {
			response.setStatus(400);
			response.setContentType("text/plain");
			response.getWriter().write(erreur);
		}
		else {
			Client modifClient = null;
			try {
				modifClient = clientDao.trouver(Integer.parseInt(idClient));
			}
			catch(DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur : "+de.getMessage());
			}
			catch(NumberFormatException nfe) {
				nfe.printStackTrace();
				response.setStatus(400);
				response.setContentType("text/plain");
				response.getWriter().write("Format idCommande invalide");
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur interne");
			}
			
			if(modifClient!=null) {
				try {
					modifClient.setNom(nom==null?modifClient.getNom():nom);
					modifClient.setPrenom(prenom==null?modifClient.getPrenom():prenom);
					modifClient.setEntreprise(entreprise==null?modifClient.getEntreprise():entreprise);
					modifClient.setEmail(email==null?modifClient.getEmail():email);
					modifClient.setTelephone(telephone==null?modifClient.getTelephone():telephone);
					modifClient.setMobile(mobile==null?modifClient.getMobile():mobile);
					modifClient.setActif(actif==null?modifClient.getActif():DataHandler.actifToBoolean(actif));
					modifClient.setNotes(notes==null?modifClient.getNotes():notes);
					clientDao.update(modifClient);
					String jsonClient = new Gson().toJson(modifClient);
					
		    		response.setContentType("application/json");
		        	response.getWriter().write(jsonClient);
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
					response.getWriter().write("Erreur interne");
				}
			}
			else {
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur : client introuvable");
			}
		}
	}
}
