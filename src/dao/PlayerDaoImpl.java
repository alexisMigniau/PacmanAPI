package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	private static final String SQL_SELECT_BY_ID = "SELECT id_player, pseudo, login, password, nationality, date_inscription, solde, ranking_points FROM player WHERE id_player = ?";

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

	@Override
	public boolean update(long id, Player player) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
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
		player.setRankingPoints(result.getInt("ranking_points"));
		return player;
	}

	private static final String SQL_SELECT_ALL = "SELECT * FROM player";

	@Override
	public List<Player> getAllPlayers() throws DAOException {

		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;

		List<Player> playerList = new ArrayList<>();

		try {
			connexion = factory.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL);
			while (resultSet.next()) {
				// String coffeeName = rs.getString("COF_NAME");
				// int supplierID = rs.getInt("SUP_ID");
				Player player = new Player();
				player = map(resultSet);
				playerList.add(player);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, statement, connexion);
		}

		return playerList;
	}
}
