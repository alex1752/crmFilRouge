package crm.model;

public class Commande {

	private int id;
	private String label;
	private double tjmht;
	private int dureejours;
	private double tva;
	private String statut;
	private String typecommande;
	private String notes;
	private int idclient;

	public Commande() {
		super();
	}

	public Commande(String label, double tjmht, int dureejours, double tva, String statut, String typecommande,
			String notes, int idclient) {
		super();
		this.label = label;
		this.tjmht = tjmht;
		this.dureejours = dureejours;
		this.tva = tva;
		this.statut = statut;
		this.typecommande = typecommande;
		this.notes = notes;
		this.idclient = idclient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public double getTjmht() {
		return tjmht;
	}

	public void setTjmht(double tjmht) {
		this.tjmht = tjmht;
	}

	public int getDureejours() {
		return dureejours;
	}

	public void setDureejours(int dureejours) {
		this.dureejours = dureejours;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public String getTypecommande() {
		return typecommande;
	}

	public void setTypecommande(String typecommande) {
		this.typecommande = typecommande;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getTva() {
		return tva;
	}

	public void setTva(double tva) {
		this.tva = tva;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int i) {
		this.idclient = i;
	}

	@Override
	public String toString() {
		return "Commande [Id : " + getId() + ", Label : " + getLabel() + ", Tjmht : " + getTjmht() + ", Dureejours : "
				+ getDureejours() + ", TVA : " + getTva() + ", Statut : " + getStatut() + ", Typecommande : "
				+ getTypecommande() + ", Notes : " + getNotes() + ", Client : " + getIdclient() + "]";
	}

}
