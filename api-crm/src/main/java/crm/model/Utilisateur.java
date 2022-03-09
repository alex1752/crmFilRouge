package crm.model;

public class Utilisateur {
	private int id;
	private String login;
	private String motDePasse;
	private String email;

	public Utilisateur(String login, String motDePasse, String email) {
		super();
		this.login = login;
		this.motDePasse = motDePasse;
		this.email = email;
	}

	public Utilisateur() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getMotDePasse() {
		return this.motDePasse;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	@Override
	public String toString() {
		return String.format("Utilisateur [Id : %d, Login : %s, Email : %s, Mot de passe : %s]", getId(), getLogin(),
				getEmail(), getMotDePasse());
	}
}
