package crm.dao;

import java.util.List;

import crm.model.Commande;

public interface CommandeDao {

	void creer(Commande commande) throws DaoException;

	Commande trouver(int id) throws DaoException;

	List<Commande> lister() throws DaoException;

	void supprimer(int id) throws DaoException;

	void update(Commande commande) throws DaoException;

	List<Commande> listerCommandeSelect(int idclient) throws DaoException;
	
	public List<Commande> listerCommandeLabel(String label) throws DaoException;
}
