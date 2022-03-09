package crm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import crm.model.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private static final String SQL_INSERT = "INSERT INTO users(login, motdepasse, email) VALUES(?,?,?)";
	private static final String SQL_SELECT = "SELECT id, login, motdepasse, email FROM users ORDER BY id ASC";
	private static final String SQL_SELECT_BY_ID = "SELECT id, login, motdepasse, email FROM users WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM users WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE users SET login = ?, motdepasse = ?, email = ? WHERE id = ? ";
	private static final String SQL_SELECT_BY_LOGIN = "SELECT login FROM users WHERE login = ?";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT email FROM users WHERE email = ?";
	private static final String SQL_SELECT_IDENTIFICATION = "SELECT login, motdepasse FROM users WHERE login = ? AND motdepasse = ?";

	private DaoFactory factory;

	public UtilisateurDaoImpl(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public Boolean Identification(String login, String motdepasse) throws DaoException {

		Connection con = null;
		ResultSet rs = null;
		Boolean resultBoolean = false;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_SELECT_IDENTIFICATION);

			pst.setString(1, login);
			pst.setString(2, motdepasse);
			rs = pst.executeQuery();

			if (rs.next()) {

				resultBoolean = true;

			}
			rs.close();
			pst.close();

		} catch (SQLException ex) {
			throw new DaoException("Erreur d'Identification", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return resultBoolean;
	}

	@Override
	public void creer(Utilisateur utilisateur) throws DaoException {
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, utilisateur.getLogin());
			pst.setString(2, utilisateur.getMotDePasse());
			pst.setString(3, utilisateur.getEmail());

			int statut = pst.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec création utilisateur (aucun ajout)");
			}
			ResultSet rsKeys = pst.getGeneratedKeys();
			if (rsKeys.next()) {
				utilisateur.setId(rsKeys.getInt(1));
			} else {
				throw new DaoException("Echec création utilisateur (ID non retourné)");
			}
			rsKeys.close();
			pst.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec création utilisateur", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public Utilisateur trouver(int id) throws DaoException {
		Utilisateur utilisateur = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				utilisateur = map(rs);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Utilisateur.", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return utilisateur;
	}
	
	@Override
	public boolean trouverLogin(String login) throws DaoException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_LOGIN);
			pst.setString(1, login);
			rs = pst.executeQuery();
			if (rs.next()) {
				existe = true;
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("Erreur de recherche BDD Utilisateur..", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return existe;
	}
	
	
	@Override
	public boolean trouverEmail(String email) throws DaoException {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean existe = false;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_EMAIL);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if (rs.next()) {
				existe = true;
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException("Erreur de recherche BDD Utilisateur...", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return existe;
	}
	
	
	
	
	

	@Override
	public List<Utilisateur> lister() throws DaoException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeUtilisateurs.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Utilisateur", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeUtilisateurs;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_ID);
			pst.setInt(1, id);
			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Erreur de suppression Utilisateur (" + id + ")");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Utilisateur", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public void update(Utilisateur utilisateur) throws DaoException {
		Connection con = null;

		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE_BY_ID);

			pst.setString(1, utilisateur.getLogin());
			pst.setString(2, utilisateur.getMotDePasse());
			pst.setString(3, utilisateur.getEmail());
			pst.setInt(4, utilisateur.getId());

			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Echec update Utilisateur (aucune modif)");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de update BDD Utilisateur", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	private static Utilisateur map(ResultSet resultSet) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(resultSet.getInt("id"));
		utilisateur.setLogin(resultSet.getString("login"));
		utilisateur.setMotDePasse(resultSet.getString("motdepasse"));
		utilisateur.setEmail(resultSet.getString("email"));
		return utilisateur;
	}
}
