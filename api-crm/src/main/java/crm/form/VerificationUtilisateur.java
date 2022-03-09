package crm.form;

import java.util.regex.Pattern;

import crm.dao.DaoException;
import crm.dao.DaoFactory;
import crm.dao.UtilisateurDao;
import crm.utils.Regex;

public class VerificationUtilisateur {
		
	public static String verificationCreationUtilisateur(String login, String motDePasse, String email) {
		UtilisateurDao utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
		String erreurs = "";
		
		if(login!=null) {
			if(!Pattern.matches(Regex.DeuxCaracteres.getTestString(), login)) {
				erreurs += "Login invalide, deux caractères minimum\n";
			}
			try {
				boolean loginExiste = utilisateurDao.trouverLogin(login);
				if(loginExiste) {
					erreurs += "Ce login existe deja\n";
				}
			}
			catch (DaoException de) {
				erreurs += de.getMessage() + "\n";
			}
		}
		else {
			erreurs += "Login requis\n";
		}
		
		if(email!=null) {
			if(!Pattern.matches(Regex.Email.getTestString(), email)) {
				erreurs += "Email invalide\n";
			}
			try {
				boolean emailExiste = utilisateurDao.trouverEmail(email);
				if(emailExiste) {
					erreurs += "Cet email existe deja\n";
				}
			}
			catch (DaoException de) {
				erreurs += de.getMessage() + "\n";
			}
		}
		else {
			erreurs += "Email requis\n";
		}
		
		if(motDePasse!=null) {
			if(!Pattern.matches(Regex.MotDePasse.getTestString(), motDePasse)) {
				erreurs += "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial\n";
			}
		}
		else {
			erreurs += "Mot de passe requis\n";
		}
		
		return erreurs;
	}
	
	public static String verificationModificationUtilisateur(String idUtilisateur, String login, String motDePasse, String email) {
		UtilisateurDao utilisateurDao = DaoFactory.getInstance().getUtilisateurDao();
		String erreurs = "";
		
		if(idUtilisateur!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), idUtilisateur)) {
				erreurs += "Format idUtilisateur incorrect\n";
			}
		}
		else {
			erreurs += "idUtilisateur requis\n";
		}
		
		if(login!=null) {
			if(!Pattern.matches(Regex.DeuxCaracteres.getTestString(), login)) {
				erreurs += "Login invalide, deux caractères minimum\n";
			}
			try {
				boolean loginExiste = utilisateurDao.trouverLogin(login);
				if(loginExiste) {
					erreurs += "Ce login existe deja\n";
				}
			}
			catch (DaoException de) {
				erreurs += de.getMessage() + "\n";
			}
		}
		
		if(email!=null) {
			if(!Pattern.matches(Regex.Email.getTestString(), email)) {
				erreurs += "Email invalide\n";
			}
			try {
				boolean emailExiste = utilisateurDao.trouverEmail(email);
				if(emailExiste) {
					erreurs += "Cet email existe deja\n";
				}
			}
			catch (DaoException de) {
				erreurs += de.getMessage() + "\n";
			}
		}
		
		if(motDePasse!=null) {
			if(!Pattern.matches(Regex.MotDePasse.getTestString(), motDePasse)) {
				erreurs += "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule, un chiffre et un caractère spécial\n";
			}
		}
		
		return erreurs;
	}
}
