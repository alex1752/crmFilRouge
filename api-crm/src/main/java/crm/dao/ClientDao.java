package crm.dao;

import java.util.List;

import crm.model.Client;

public interface ClientDao {

	void creer(Client client) throws DaoException;

	Client trouver(int id) throws DaoException;

	List<Client> lister() throws DaoException;
	
	List<Client> rechercherParNomEtPrenom( String search ) throws DaoException;

	void supprimer(int id) throws DaoException;

	void update(Client client) throws DaoException;

	void switchStatus(int id) throws DaoException;
	
	boolean trouver(String nomClient, String prenomClient ) throws DaoException;

	boolean trouver(String nomClient, String prenomClient, int idClient ) throws DaoException;
}
