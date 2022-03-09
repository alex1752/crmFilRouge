package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crm.model.Client;

public class ClientDaoImpl implements ClientDao {

	private static final String SQL_INSERT = "INSERT INTO clients(nom, prenom, entreprise, email, telephone, mobile, actif, notes) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT id,nom,prenom,entreprise,email,telephone,mobile,actif,notes FROM clients ORDER BY id ASC";
	private static final String SQL_SELECT_BY_ID = "SELECT id,nom,prenom,entreprise,email,telephone,mobile,actif,notes FROM clients WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM clients WHERE id = ? ";
	private static final String SQL_SWITCH_BY_ID = "UPDATE clients SET actif = NOT actif WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE clients SET nom = ?, prenom = ?, entreprise = ?, email = ?, telephone = ?, mobile = ?, actif = ?, notes = ? WHERE id = ? ";
	private static final String SQL_SELECT_BY_NAME = "SELECT id,nom,prenom,entreprise,email,telephone,mobile,actif,notes FROM clients WHERE nom = ? AND prenom = ?";
	private static final String SQL_SELECT_BY_NAME_UPDATE="SELECT id, nom, prenom FROM clients WHERE nom=? AND prenom=? EXCEPT SELECT id, nom, prenom FROM clients WHERE id=?";
	private static final String SQL_SELECT_BY_NOM_OR_PRENOM = "SELECT id,nom,prenom,entreprise,email,telephone,mobile,actif,notes FROM clients WHERE nom LIKE CONCAT('%',?,'%') OR prenom LIKE CONCAT('%',?,'%') ";
	private DaoFactory factory;

	public ClientDaoImpl(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public void creer(Client client) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getEntreprise());
			pst.setString(4, client.getEmail());
			pst.setString(5, client.getTelephone());
			pst.setString(6, client.getMobile());
			pst.setBoolean(7, client.getActif());
			pst.setString(8, client.getNotes());

			int statut = pst.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec création Client (aucun ajout)");
			}
			ResultSet rsKeys = pst.getGeneratedKeys();
			if (rsKeys.next()) {
				client.setId(rsKeys.getInt(1));
			} else {
				throw new DaoException("Echec création Clinet (ID non retourné)");
			}
			rsKeys.close();
			pst.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec création Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	

	@Override
	public Client trouver(int id) throws DaoException {
		// TODO Auto-generated method stub
		Client client = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				client = map(rs);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return client;
	}

	@Override
	public List<Client> lister() throws DaoException {
		// TODO Auto-generated method stub
		List<Client> listeClients = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeClients.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeClients;
	}
	
	
	

	@Override
	public List<Client> rechercherParNomEtPrenom(String search) throws DaoException {
		List<Client> listeClients = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_NOM_OR_PRENOM);
			pst.setString(1, search);
			pst.setString(2, search);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeClients.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeClients;
	}

	@Override
	public void supprimer(int id) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_DELETE_BY_ID);
			pst.setInt(1, id);
			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Erreur de suppression Client (" + id + ")");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}
	
	@Override
	public void switchStatus(int id) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SWITCH_BY_ID);
			pst.setInt(1, id);
			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Erreur de suppression Client (" + id + ")");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public void update(Client client) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE_BY_ID);

			pst.setString(1, client.getNom());
			pst.setString(2, client.getPrenom());
			pst.setString(3, client.getEntreprise());
			pst.setString(4, client.getEmail());
			pst.setString(5, client.getTelephone());
			pst.setString(6, client.getMobile());
			pst.setBoolean(7, client.getActif());
			pst.setString(8, client.getNotes());
			pst.setInt(9, client.getId());

			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Echec update Client (aucune modif)");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur update BDD Client", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}
	
	@Override
	public boolean trouver(String nomClient, String prenomClient) throws DaoException {
        Connection        con=null;
        PreparedStatement pst=null;
        ResultSet rs= null;
        boolean existe=false;
        try {
            con=factory.getConnection();
            pst = con.prepareStatement(SQL_SELECT_BY_NAME);
            pst.setString(1, nomClient);
            pst.setString(2, prenomClient);
            rs = pst.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            pst.close();
        }catch (SQLException ex) {
            throw new DaoException ("Erreur de recherche BDD Client", ex);
        }finally {
            factory.releaseConnection(con);
        }
        return existe;
	}
	@Override
	public boolean trouver(String nomClient, String prenomClient, int idClient) throws DaoException {
        Connection        con=null;
        PreparedStatement pst=null;
        ResultSet rs= null;
        boolean existe=false;
        try {
            con=factory.getConnection();
            pst = con.prepareStatement(SQL_SELECT_BY_NAME_UPDATE);
            pst.setString(1, nomClient);
            pst.setString(2, prenomClient);
            pst.setInt(3, idClient);
            rs = pst.executeQuery();
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            pst.close();
        }catch (SQLException ex) {
            throw new DaoException ("Erreur de recherche BDD Client", ex);
        }finally {
            factory.releaseConnection(con);
        }
        return existe;
	}

	private static Client map(ResultSet resultSet) throws SQLException {
		Client c = new Client();
		c.setId(resultSet.getInt("id"));
		c.setNom(resultSet.getString("nom"));
		c.setPrenom(resultSet.getString("prenom"));
		c.setEntreprise(resultSet.getString("entreprise"));
		c.setEmail(resultSet.getString("email"));
		c.setTelephone(resultSet.getString("telephone"));
		c.setMobile(resultSet.getString("mobile"));
		c.setActif(resultSet.getBoolean("actif"));
		c.setNotes(resultSet.getString("notes"));

		return c;
	}





}
