package crm.model;

public class Client {

	private int id;
	private String nom;
	private String prenom;
	private String entreprise;
	private String email;
	private String telephone;
	private String mobile;
	private Boolean actif = true;
	private String notes;

	public Client() {
		super();
	}

	public Client(String nom, String prenom, String entreprise, String email, String telephone, String mobile,
			Boolean actif, String notes) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.entreprise = entreprise;
		this.email = email;
		this.telephone = telephone;
		this.mobile = mobile;
		this.actif = actif;
		this.notes = notes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Client [Id : " + getId() + ", Nom : " + getNom() + ", Prenom : " + getPrenom() + ", Entreprise : "
				+ getEntreprise() + ", Email : " + getEmail() + ", Telephone : " + getTelephone() + ", Mobile : "
				+ getMobile() + ", Actif : " + getActif() + ", Notes : " + getNotes() + "]";
	}

}
