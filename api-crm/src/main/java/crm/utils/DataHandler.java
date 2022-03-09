package crm.utils;

import com.google.gson.JsonObject;

public class DataHandler {
	
	public static String getNullOrString(JsonObject jsonObject, String key) {
		return jsonObject.get(key)==null ? null : jsonObject.get(key).getAsString().trim();
	}
	
	public static boolean actifToBoolean(String statut) {
		boolean actif = false;
		switch(statut.toUpperCase()) {
			case "OUI" :
			case "TRUE" :
				actif = true;
				break;
			case "NON" :
			case "FALSE" :
				actif = false;
				break;
		}
		return actif;
	}
}

