package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.DAOUtil.*;

import bean.Player;

public class PlayerDaoImpl implements PlayerDao {

	private DAOFactory factory;

	public PlayerDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String SQL_INSERT = "INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `date_inscription`) VALUES (NULL, ?, ?, MD5(?), ?, CURRENT_TIMESTAMP)";

	@Override
	public void ajouter(Player player) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_INSERT, true, player.getPseudo(), player.getLogin(),
					player.getPassword(), player.getNationality());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if (statut == 0) {
				throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
			}
			/* Récupération de l'id auto-généré par la requête d'insertion */
			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if (valeursAutoGenerees.next()) {
				/* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
				player.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM player WHERE player.login = ? AND player.password = MD5(?)";

	@Override
	public Player findByLogin(String login, String password) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Player player = null;

		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_BY_LOGIN, false, login, password);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				player = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return player;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT id_player, pseudo, login, password, nationality, date_inscription, solde FROM player WHERE id_player = ?";

	@Override
	public Player findById(Long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Player player = null;

		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				player = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return player;
	}

	private static final String SQL_UPDATE = "UPDATE player SET pseudo = ?, login = ?, nationality = ? WHERE id_player = ?";
	private static final String SQL_UPDATE_WITH_PASSWORD = "UPDATE player SET pseudo = ?, login = ?, nationality = ?, password = MD5(?) WHERE id_player = ?";
	
	@Override
	public void update(Player player) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = factory.getConnection();
			
			// Si le mot de passe est différent de null, ça veut dire qu'on doit le modifier
			if(player.getPassword() == null)
				preparedStatement = initRequest(connexion, SQL_UPDATE, false, player.getPseudo(), player.getLogin(),player.getNationality(),player.getId());
			else
				preparedStatement = initRequest(connexion, SQL_UPDATE_WITH_PASSWORD, false, player.getPseudo(), player.getLogin(),player.getNationality(),player.getPassword(),player.getId());
			
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'update */
			if (statut == 0) {
				throw new DAOException("Échec de la mise à jour des informations");
			}
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	private static final String SQL_DELETE = " DELETE FROM player WHERE id_player = ?";
	
	@Override
	public void delete(long id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_DELETE, false, id);
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête Delete */
			if (statut == 0) {
				throw new DAOException("Échec de la suppression du compte");
			}	
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement, connexion);
		}
	}

	// Transformation d'un résultat SQL en bean
	public static Player map(ResultSet result) throws SQLException {
		Player player = new Player();
		player.setId(result.getLong("id_player"));
		player.setPseudo(result.getString("pseudo"));
		player.setPassword(result.getString("password"));
		player.setLogin(result.getString("login"));
		player.setNationality(result.getString("nationality"));
		player.setDateInscription(result.getTimestamp("date_inscription"));
		player.setSolde(result.getInt("solde"));
		return player;
	}

	
}
