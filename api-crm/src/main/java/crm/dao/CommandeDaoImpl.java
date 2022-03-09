package crm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import crm.model.Commande;

public class CommandeDaoImpl implements CommandeDao {

	private static final String SQL_INSERT = "INSERT INTO orders(label, tjmht, dureejours, tva, statut, typecommande, notes, idclient) VALUES(?,?,?,?,?,?,?,?)";
	private static final String SQL_SELECT = "SELECT id,label, tjmht, dureejours, tva, statut, typecommande, notes, idclient FROM orders ORDER BY id ASC";
	private static final String SQL_SELECT_BY_ID = "SELECT id,label, tjmht, dureejours, tva, statut, typecommande, notes, idclient FROM orders WHERE id = ?";
	private static final String SQL_DELETE_BY_ID = "DELETE FROM orders WHERE id = ? ";
	private static final String SQL_UPDATE_BY_ID = "UPDATE orders SET label = ?, tjmht = ?, dureejours = ?, tva = ?, statut = ?, typecommande = ?, notes = ?, idclient = ? WHERE id = ? ";
	private static final String SQL_SELECT_BY_ID_CLIENT =  "SELECT id,label, tjmht, dureejours, tva, statut, typecommande, notes, idclient FROM orders WHERE idclient = ?";
	private static final String SQL_SELECT_BY_LABEL =  "SELECT id,label, tjmht, dureejours, tva, statut, typecommande, notes, idclient FROM orders WHERE label LIKE CONCAT('%',?,'%') ORDER BY label";
	
	private DaoFactory factory;

	public CommandeDaoImpl(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public void creer(Commande commande) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = factory.getConnection();

			PreparedStatement pst = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, commande.getLabel());
			pst.setDouble(2, commande.getTjmht());
			pst.setInt(3, commande.getDureejours());
			pst.setDouble(4, commande.getTva());
			pst.setString(5, commande.getStatut());
			pst.setString(6, commande.getTypecommande());
			pst.setString(7, commande.getNotes());
			pst.setInt(8, commande.getIdclient());

			int statut = pst.executeUpdate();

			if (statut == 0) {
				throw new DaoException("Echec création Commande (aucun ajout)");
			}
			ResultSet rsKeys = pst.getGeneratedKeys();
			if (rsKeys.next()) {
				commande.setId(rsKeys.getInt(1));
			} else {
				throw new DaoException("Echec création Commande (ID non retourné)");
			}
			rsKeys.close();
			pst.close();

		} catch (SQLException ex) {
			throw new DaoException("Echec création Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public Commande trouver(int id) throws DaoException {
		// TODO Auto-generated method stub
		Commande commande = null;
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = factory.getConnection();
			pst = con.prepareStatement(SQL_SELECT_BY_ID);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				commande = map(rs);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de recherche BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return commande;
	}

	
	@Override
	public List<Commande> listerCommandeSelect(int idclient) throws DaoException {
		// TODO Auto-generated method stub
		List<Commande> listeCommandes = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_ID_CLIENT);
			pst.setInt(1, idclient);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeCommandes.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeCommandes;
	}
	
	@Override
	public List<Commande> listerCommandeLabel(String label) throws DaoException {
		// TODO Auto-generated method stub
		List<Commande> listeCommandes = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT_BY_LABEL);
			pst.setString(1, label);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeCommandes.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeCommandes;
	}
		
	@Override
	public List<Commande> lister() throws DaoException {
		// TODO Auto-generated method stub
		List<Commande> listeCommandes = new ArrayList<>();
		Connection con = null;
		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_SELECT);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				listeCommandes.add(map(rs));
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de lecture BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
		return listeCommandes;
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
				throw new DaoException("Erreur de suppression Commande (" + id + ")");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de suppression BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}
	}

	@Override
	public void update(Commande commande) throws DaoException {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = factory.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_UPDATE_BY_ID);

			pst.setString(1, commande.getLabel());
			pst.setDouble(2, commande.getTjmht());
			pst.setInt(3, commande.getDureejours());
			pst.setDouble(4, commande.getTva());
			pst.setString(5, commande.getStatut());
			pst.setString(6, commande.getTypecommande());
			pst.setString(7, commande.getNotes());
			pst.setInt(8, commande.getIdclient());
			pst.setInt(9, commande.getId());

			int statut = pst.executeUpdate();
			if (statut == 0) {
				throw new DaoException("Echec update Commande (aucune modif)");
			}
			pst.close();
		} catch (SQLException ex) {
			throw new DaoException("Erreur de update BDD Commande", ex);
		} finally {
			factory.releaseConnection(con);
		}

	}

	private static Commande map(ResultSet resultSet) throws SQLException {

		Commande co = new Commande();
		co.setId(resultSet.getInt("id"));
		co.setLabel(resultSet.getString("label"));
		co.setTjmht(resultSet.getDouble("tjmht"));
		co.setDureejours(resultSet.getInt("dureejours"));
		co.setTva(resultSet.getDouble("tva"));
		co.setStatut(resultSet.getString("statut"));
		co.setTypecommande(resultSet.getString("typecommande"));
		co.setNotes(resultSet.getString("notes"));
		co.setIdclient(resultSet.getInt("idclient"));

		return co;
	}

}
