package crm.utils;

public enum Regex {
	UnCaractere("(^[A-Za-zÀ-ÿ\\d]$|^[A-Za-zÀ-ÿ\\d][\\dA-Za-zÀ-ÿ \\-']*[\\dA-Za-zÀ-ÿ]$)"),
	DeuxCaracteres("^[\\dA-Za-zÀ-ÿ][\\dA-Za-zÀ-ÿ \\-']*[\\dA-Za-zÀ-ÿ]$"),
	Email("(^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$)"),
	NombreDeuxDecimales("^[1-9]\\d*((\\.|,)\\d{1,2})?$"),
	NombreEntier("^[1-9]\\d*$"),
	Pourcentage("^([1-9]\\d?)((\\.|,)\\d{1,2})?$|^100((\\.|,)00?)?$|0((\\.|,)\\d{1,2})?$"),
	Telephone("^0[1-9][0-9]{8}$"),
	Statut("^(CONFIRME|ANNULE|OPTION)$"),
	Actif("^(OUI|TRUE|NON|FALSE)$"),
	Type("^(Formation|Prestation|FORMATION|PRESTATION)$"),
	MotDePasse("(?=^[^ ]{8,}$)(?=.*[@$!%*#?&+_\\-])(?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).*");
	//("(^[A-Za-zÀ-ÿ\\d]$|^[A-Za-zÀ-ÿ\\d][A-Za-zÀ-ÿ\\d\\-']*[A-Za-zÀ-ÿ\\d]$)")
	private String testString;
	
	Regex(String testString) {
		this.testString = testString;
	}
	
	public String getTestString() {
		return testString;
	}
}
