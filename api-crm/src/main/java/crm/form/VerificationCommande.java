package crm.form;

import java.util.regex.Pattern;

import crm.utils.Regex;

public class VerificationCommande {
	
	
	public static String verificationCreationCommande(String label, String tjmht, String dureeJours, String tva, String statut, String typeCommande, String notes, String idClient) {
		String erreurs = "";
		
		if(label!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), label)) {
				erreurs += "Intitulé invalide, un caratère minimum\n";
			}
		}
		
		if(tjmht!=null) {
			if(!Pattern.matches(Regex.NombreDeuxDecimales.getTestString(), tjmht)) {
				erreurs += "Format de tjmht incorrect\n";
			}
		}
		else {
			erreurs += "TJM HT requis\n";
		}
		
		if(dureeJours!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), dureeJours)) {
				erreurs += "Format de durée incorrect\n";
			}
		}
		else {
			erreurs += "Durée requise\n";
		}
		
		if(tva!=null) {
			if(!Pattern.matches(Regex.Pourcentage.getTestString(), tva)) {
				erreurs += "Format de tva incorrect\n";
			}
		}
		else {
			erreurs += "TVA requise\n";
		}
		
		if(statut!=null) {
			if(!Pattern.matches(Regex.Statut.getTestString(), statut)) {
				erreurs += "Statut invalide\n";
			}
		}
		else {
			erreurs += "Statut requis\n";
		}
		
		if(typeCommande!=null) {
			if(!Pattern.matches(Regex.Type.getTestString(), typeCommande)) {
				erreurs += "Type invalide\n";
			}
		}
		else {
			erreurs += "Type requis\n";
		}
		
		if(notes!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), notes)) {
				erreurs += "Intitulé invalide, un caratère minimum\n";
			}
		}
		
		if(idClient!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), idClient)) {
				erreurs += "Format idClient incorrect\n";
			}
		}
		else {
			erreurs += "idClient requis\n";
		}
		
		return erreurs;
	}
	
	public static String verificationModificationCommande(String idCommande, String label, String tjmht, String dureeJours, String tva, String statut, String typeCommande, String notes, String idClient) {
		String erreurs = "";
		
		if(idCommande!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), idCommande)) {
				erreurs += "Format idCommande incorrect\n";
			}
		}
		else {
			erreurs += "idCommande requis\n";
		}
		
		if(label!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), label)) {
				erreurs += "Intitulé invalide, un caratère minimum\n";
			}
		}
		
		if(tjmht!=null) {
			if(!Pattern.matches(Regex.NombreDeuxDecimales.getTestString(), tjmht)) {
				erreurs += "Format de tjmht incorrect\n";
			}
		}
		
		if(dureeJours!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), dureeJours)) {
				erreurs += "Format de durée incorrect\n";
			}
		}
		if(tva!=null) {
			if(!Pattern.matches(Regex.Pourcentage.getTestString(), tva)) {
				erreurs += "Format de tva incorrect\n";
			}
		}
		
		if(statut!=null) {
			if(!Pattern.matches(Regex.Statut.getTestString(), statut)) {
				erreurs += "Statut invalide\n";
			}
		}
		
		if(typeCommande!=null) {
			if(!Pattern.matches(Regex.Type.getTestString(), typeCommande)) {
				erreurs += "Type invalide\n";
			}
		}
		
		if(notes!=null) {
			if(!Pattern.matches(Regex.UnCaractere.getTestString(), notes)) {
				erreurs += "Intitulé invalide, un caratère minimum\n";
			}
		}
		
		if(idClient!=null) {
			if(!Pattern.matches(Regex.NombreEntier.getTestString(), idClient)) {
				erreurs += "Format idClient incorrect\n";
			}
		}
		
		return erreurs;
	}
}
