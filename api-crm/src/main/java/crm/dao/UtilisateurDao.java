package crm.dao;

import java.util.List;

import crm.model.Utilisateur;

public interface UtilisateurDao {
	void creer(Utilisateur utilisateur) throws DaoException;

	Utilisateur trouver(int id) throws DaoException;

	List<Utilisateur> lister() throws DaoException;

	void supprimer(int id) throws DaoException;

	void update(Utilisateur utilisateur) throws DaoException;

	Boolean Identification(String login, String motdepasse) throws DaoException;

	boolean trouverLogin(String login) throws DaoException;

	boolean trouverEmail(String email) throws DaoException;
}
