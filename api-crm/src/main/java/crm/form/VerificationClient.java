package crm.form;

import java.util.regex.Pattern;

import crm.utils.Regex;

public class VerificationClient {
	
	
	public static String verificationCreationClient(String nom, String prenom, String entreprise, String email, String telephone, String mobile, String notes) {
		String erreurs = "";
		
		if(nom!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), nom)) {
				erreurs += "Nom invalide, un caractère minimum\n";
			}
		}
		else {
			erreurs += "Nom requis\n";
		}
		
		if(prenom!=null) {
			if(!Pattern.matches(Regex.DeuxCaracteres.getTestString(), prenom)) {
				erreurs += "Prenom invalide, deux caractères minimum\n";
			}
		}
		else {
			erreurs += "Prénom requis\n";
		}
		
		if(entreprise!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), entreprise)) {
				erreurs += "Entreprise invalide, un caractère minimum\n";
			}
		}
		
		if(email!=null) {
			if(!Pattern.matches(Regex.Email.getTestString(), email)) {
				erreurs += "Email invalide\n";
			}
		}
		else {
			erreurs += "Email requis\n";
		}
		
		if(telephone!=null) {
			if(!Pattern.matches(Regex.Telephone.getTestString(), telephone)) {
				erreurs += "Telephone invalide\n";
			}
		}
		
		if(mobile!=null) {
			if(!Pattern.matches(Regex.Telephone.getTestString(), mobile)) {
				erreurs += "Mobile invalide\n";
			}
		}
		
		return erreurs;
	}
	
	public static String verificationModificationClient(String idClient, String nom, String prenom, String entreprise, String email, String telephone, String mobile, String actif, String notes) {
		String erreurs = "";
		
		if(idClient!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), idClient)) {
				erreurs += "Format idClient invalide\n";
			}
		}
		else {
			erreurs += "idClient requis\n";
		}
		
		if(nom!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), nom)) {
				erreurs += "Nom invalide, un caractère minimum\n";
			}
		}
		
		if(prenom!=null) {
			if(!Pattern.matches(Regex.DeuxCaracteres.getTestString(), prenom)) {
				erreurs += "Prenom invalide, deux caractères minimum\n";
			}
		}
		
		if(entreprise!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), entreprise)) {
				erreurs += "Entreprise invalide, un caractère minimum\n";
			}
		}
		
		if(email!=null) {
			if(!Pattern.matches(Regex.Email.getTestString(), email)) {
				erreurs += "Email invalide\n";
			}
		}
		
		if(telephone!=null) {
			if(!Pattern.matches(Regex.Telephone.getTestString(), telephone)) {
				erreurs += "Telephone invalide\n";
			}
		}
		
		if(mobile!=null) {
			if(!Pattern.matches(Regex.Telephone.getTestString(), mobile)) {
				erreurs += "Mobile invalide\n";
			}
		}
		
		if(actif!=null) {
			if(!Pattern.matches(Regex.Actif.getTestString(), actif.toUpperCase())) {
				erreurs += "Statut actif incorrect";
			}
		}
		
		return erreurs;
	}
}
