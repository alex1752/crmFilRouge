package crm.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
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
import crm.dao.CommandeDao;
import crm.dao.DaoException;
import crm.dao.DaoFactory;
import crm.form.VerificationCommande;
import crm.model.Client;
import crm.model.Commande;
import crm.utils.DataHandler;

/**
 * Servlet implementation class CRUD_Commande
 */
@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CHAMP_ID_COMMANDE = "id";
	private static final String CHAMP_LABEL = "label";
	private static final String CHAMP_TJMHT = "tjmht";
	private static final String CHAMP_DUREE = "dureejours";
	private static final String CHAMP_TVA = "tva";
	private static final String CHAMP_STATUT = "statut";
	private static final String CHAMP_TYPE = "typecommande";
	private static final String CHAMP_NOTES = "notes";
	private static final String CHAMP_ID_CLIENT = "idclient";
	private static final String CHAMP_RECHERCHE = "search";
	
	
	
	private CommandeDao commandeDao;
	private ClientDao clientDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(){
		this.commandeDao = DaoFactory.getInstance().getCommandeDao();
		this.clientDao = DaoFactory.getInstance().getClientDao();
	}

	/*Liste, details*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		JsonArray jsonCommande = new JsonArray();
		if(request.getParameter("id")!=null) {//Details commande
			Commande commandeDetail = null;
			try {
				int idCommande = Integer.parseInt(request.getParameter("id"));
				commandeDetail = commandeDao.trouver(idCommande);
				jsonCommande.add(JsonParser.parseString(new Gson().toJson(commandeDetail)).getAsJsonObject());
//				jsonCommande.add(1);
				response.setContentType("application/json");
				response.getWriter().write(jsonCommande.toString());
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
				response.getWriter().write("Erreur interne");
			}
		}
		else if (request.getParameter(CHAMP_RECHERCHE)!=null) {//d�tails commande label
			List<Commande> commandeLabel = null;
			try {
				String label = request.getParameter(CHAMP_RECHERCHE);
				commandeLabel = commandeDao.listerCommandeLabel(label);
				jsonCommande = JsonParser.parseString(new Gson().toJson(commandeLabel)).getAsJsonArray();
//				int size = commandeLabel.size();
//				jsonCommande.add(size);
				response.setContentType("application/json");
				response.getWriter().write(jsonCommande.toString());
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
				response.getWriter().write("Erreur interne");
			}
		}
		else {//Liste Commandes
			try {
				jsonCommande = JsonParser.parseString(new Gson().toJson(commandeDao.lister())).getAsJsonArray();
//				int size = commandeDao.lister().size();
//				jsonCommande.add(size);
				response.setContentType("application/json");
				response.getWriter().write(jsonCommande.toString());
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
				response.getWriter().write("Erreur interne");
			}
		}
	}

	/*Creation commande*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String ligne = null;
		BufferedReader reader = request.getReader();
		String erreur = "", label = null, tjmht = null, dureeJours = null, tva = null, statut = null, typeCommande = null, notes = null, idClient = null;
		
		while((ligne=reader.readLine()) != null) {
			buffer.append(ligne);
		}
		
		
		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			label = DataHandler.getNullOrString( data, CHAMP_LABEL );
	        tjmht = DataHandler.getNullOrString( data, CHAMP_TJMHT );
	        dureeJours = DataHandler.getNullOrString( data, CHAMP_DUREE );
	        tva = DataHandler.getNullOrString( data, CHAMP_TVA);
	        statut = DataHandler.getNullOrString( data, CHAMP_STATUT );
	        typeCommande = DataHandler.getNullOrString( data, CHAMP_TYPE );
	        notes = DataHandler.getNullOrString( data, CHAMP_NOTES );
	        idClient = DataHandler.getNullOrString( data, CHAMP_ID_CLIENT );
	        
	        erreur = VerificationCommande.verificationCreationCommande(label, tjmht, dureeJours, tva, statut, typeCommande, notes, idClient);
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
			Client client = null;
			try {
				client = clientDao.trouver(Integer.parseInt(idClient));
			}
			catch(DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Client introuvable");
			}
			catch(NumberFormatException nfe) {
				nfe.printStackTrace();
				response.setStatus(400);
				response.setContentType("text/plain");
				response.getWriter().write("Format idClient invalide");
			}
			catch (Exception e) {
				e.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Erreur interne");
			}
			
			if(client!=null) {
				try {
					double tjmhtDouble = Double.parseDouble(tjmht.replace(",","."));
					double tvaDouble = Double.parseDouble(tva.replace(",","."));
					int dureeJoursInt = Integer.parseInt(dureeJours);
					int idClientInt = Integer.parseInt(idClient);
					Commande nouvelleCommande = new Commande(label, tjmhtDouble, dureeJoursInt, tvaDouble, statut, typeCommande, notes, idClientInt);
					commandeDao.creer(nouvelleCommande);
					String jsonNouvelleCommande = new Gson().toJson(nouvelleCommande);
					response.setContentType("application/json");
					response.getWriter().write(jsonNouvelleCommande);
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

	/*Modification commande*/
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		StringBuffer buffer = new StringBuffer();
		String ligne = null;
		BufferedReader reader = request.getReader();
		String erreur = "", idCommande = null, label = null, tjmht = null, dureeJours = null, tva = null, statut = null, typeCommande = null, notes = null, idClient = null;
		
		while((ligne=reader.readLine()) != null) {
			buffer.append(ligne);
		}
		
		
		try {
			JsonObject data = JsonParser.parseString(buffer.toString()).getAsJsonObject();
			
			idCommande = DataHandler.getNullOrString( data, CHAMP_ID_COMMANDE );
			label = DataHandler.getNullOrString( data, CHAMP_LABEL );
	        tjmht = DataHandler.getNullOrString( data, CHAMP_TJMHT );
	        dureeJours = DataHandler.getNullOrString( data, CHAMP_DUREE );
	        tva = DataHandler.getNullOrString( data, CHAMP_TVA );
	        statut = DataHandler.getNullOrString( data, CHAMP_STATUT );
	        typeCommande = DataHandler.getNullOrString( data, CHAMP_TYPE );
	        notes = DataHandler.getNullOrString( data, CHAMP_NOTES );
	        idClient = DataHandler.getNullOrString( data, CHAMP_ID_CLIENT );
	        
	        erreur = VerificationCommande.verificationModificationCommande(idCommande, label, tjmht, dureeJours, tva, statut, typeCommande, notes, idClient);
		}
		catch (JsonSyntaxException jse) {
			jse.printStackTrace();
			response.setStatus(400);
			response.setContentType("text/plain");
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
			Commande modifCommande = null;
			try {
				modifCommande = commandeDao.trouver(Integer.parseInt(idCommande));
			}
			catch(DaoException de) {
				de.printStackTrace();
				response.setStatus(500);
				response.setContentType("text/plain");
				response.getWriter().write("Commande introuvable");
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
			if(idClient!=null) {
				try {
					clientDao.trouver(Integer.parseInt(idClient));
				}
				catch(DaoException de) {
					de.printStackTrace();
					response.setStatus(500);
					response.setContentType("text/plain");
					response.getWriter().write("Client introuvable");
				}
				catch(NumberFormatException nfe) {
					nfe.printStackTrace();
					response.setStatus(400);
					response.setContentType("text/plain");
					response.getWriter().write("Format idClient invalide");
				}
				catch (Exception e) {
					e.printStackTrace();
					response.setStatus(500);
					response.setContentType("text/plain");
					response.getWriter().write("Erreur interne");
				}
			}
			if(modifCommande!=null) {
				try {
					modifCommande.setLabel(label==null?modifCommande.getLabel():label);
					modifCommande.setTjmht(tjmht==null?modifCommande.getTjmht():Double.parseDouble(tjmht.replace(",",".")));
					modifCommande.setDureejours(dureeJours==null?modifCommande.getDureejours():Integer.parseInt(dureeJours));
					modifCommande.setTva(tva==null?modifCommande.getTva():Double.parseDouble(tva.replace(",",".")));
					modifCommande.setStatut(statut==null?modifCommande.getStatut():statut);
					modifCommande.setTypecommande(typeCommande==null?modifCommande.getTypecommande():typeCommande);
					modifCommande.setNotes(statut==null?modifCommande.getNotes():notes);
					modifCommande.setIdclient(idClient==null?modifCommande.getIdclient():Integer.parseInt(idClient));
					
					commandeDao.update(modifCommande);
					
					String jsonModifCommande = new Gson().toJson(modifCommande);
					response.setContentType("application/json");
					response.getWriter().write(jsonModifCommande);
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
				response.getWriter().write("Erreur : commande introuvable");
			}
		}
	}

	/*Suppression commande*/
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String idCommande = request.getParameter(CHAMP_ID_COMMANDE);
		try {
			int idCommandeInt = Integer.parseInt(idCommande);
			commandeDao.supprimer(idCommandeInt);
			response.setContentType("text/plain");
			response.getWriter().write("Suppression effectuée");
		}
		catch (DaoException de) {
			de.printStackTrace();
			response.setStatus(500);
			response.setContentType("text/plain");
			response.getWriter().write(de.getMessage());
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
			response.getWriter().write("Erreur interne");
		}
	}
}
	
	
