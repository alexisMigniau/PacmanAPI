package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.DAOUtil.*;

import bean.Cosmetic;
import bean.Player;

public class CosmeticDaoImpl implements CosmeticDao {

	private DAOFactory factory;

	public CosmeticDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String SQL_INSERT_PLAYER_COSMETIC = "INSERT INTO `player_cosmetic`  (`id_possession_cosmetic`,`id_cosmetic`, `id_player`) VALUES (NULL, ?, ?)";

	// Verifier que le joueur n a pas deja la cosmetic en question
	// AJouter une ligne dans player_cometic
	// Debiter le solde du joueur
	@Override
	public Cosmetic buyCosmetic(List<Object> requestResult) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cosmetic cosmetic = null;
		int id_cosmetic = Integer.parseInt((String) requestResult.get(0));
		int id_player = Integer.parseInt((String) requestResult.get(1));
		int price = Integer.parseInt((String) requestResult.get(2));
		int solde = Integer.parseInt((String) requestResult.get(3));

		System.out.println(
				"id_cosmetic " + id_cosmetic + " id_player " + id_player + " price " + price + " solde " + solde);

		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_INSERT_PLAYER_COSMETIC, true, id_cosmetic, id_player);
			int statut = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return cosmetic;
	}

	private static final String SQL_UPDATE_SOLDE_PLAYER = "UPDATE `player` SET solde = ? WHERE player.id_player = ?";

	@Override
	public Cosmetic debiteSolde(List<Object> requestResult) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cosmetic cosmetic = null;
		int id_cosmetic = Integer.parseInt((String) requestResult.get(0));
		int id_player = Integer.parseInt((String) requestResult.get(1));
		int price = Integer.parseInt((String) requestResult.get(2));
		int solde = Integer.parseInt((String) requestResult.get(3));
		int newSolde = solde - price;
		System.out.println("newSolde " + newSolde);
		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_UPDATE_SOLDE_PLAYER, true, newSolde, id_player);
			int statut = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return cosmetic;
	}

	private static final String SQL_UPDATE_SOLDE = "UPDATE player SET solde = ? WHERE id_player = ?";

	@Override
	public void crediterSolde(List<Object> requestResult, int amount) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet query_prepared = null;
		Cosmetic cos = null;

		int id_player = Integer.parseInt((String) requestResult.get(1));
		int solde = Integer.parseInt((String) requestResult.get(3));
		int newSolde = solde + amount;

		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_UPDATE_SOLDE, true, newSolde, id_player);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(query_prepared, preparedStatement, connexion);
		}
	}

	private static final String SQL_SELECT_ALL = "SELECT id_cosmetic, name, price FROM cosmetic";

	@Override
	public List<Cosmetic> findAll() throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cosmetic cosmetic = null;
		List<Cosmetic> listCosmetic = new ArrayList<Cosmetic>();
		;
		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				cosmetic = map(resultSet);
				listCosmetic.add(cosmetic);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return listCosmetic;
	}

	private static final String SQL_SELECT_ALL_POSSESSED = "SELECT DISTINCT pc.id_cosmetic, name, price FROM cosmetic INNER JOIN player_cosmetic pc ON cosmetic.id_cosmetic = pc.id_cosmetic WHERE id_player = ?	";

	@Override
	public List<Cosmetic> findAllCosmeticPossessedByPlayer(Long id_player) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cosmetic cosmetic = null;
		List<Cosmetic> listCosmeticPossessed = new ArrayList<Cosmetic>();
		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_ALL_POSSESSED, false, id_player);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cosmetic = map(resultSet);
				listCosmeticPossessed.add(cosmetic);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return listCosmeticPossessed;
	}

	private static final String SQL_SELECT_ALL_NOT_BUY = "SELECT c.id_cosmetic, c.name, c.price FROM cosmetic c WHERE c.id_cosmetic NOT IN ( SELECT DISTINCT pc.id_cosmetic FROM cosmetic INNER JOIN player_cosmetic pc ON cosmetic.id_cosmetic = pc.id_cosmetic WHERE id_player = ? )	";

	@Override
	public List<Cosmetic> findAllCosmeticNotBuy(Long id_player) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Cosmetic cosmetic = null;
		List<Cosmetic> listCosmeticNotPossessed = new ArrayList<Cosmetic>();
		try {
			// Ouverture de la connexion
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_ALL_NOT_BUY, false, id_player);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cosmetic = map(resultSet);
				listCosmeticNotPossessed.add(cosmetic);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

		return listCosmeticNotPossessed;
	}

	@Override
	public boolean update(long id, Cosmetic cosmetic) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long id) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

	// Transformation d'un résultat SQL en bean
	public static Cosmetic map(ResultSet result) throws SQLException {
		Cosmetic cosmetic = new Cosmetic();
		cosmetic.setId(result.getLong("id_cosmetic"));
		cosmetic.setName(result.getString("name"));
		cosmetic.setPrice(result.getString("price"));
		return cosmetic;
	}
}
