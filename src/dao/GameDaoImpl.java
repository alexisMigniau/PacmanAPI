package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.DAOUtil.*;

import bean.Game;
import bean.Player;

public class GameDaoImpl implements GameDao {

	private DAOFactory factory;

	public GameDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String SQL_SELECT_ALL = "SELECT * FROM game";
	private static final String SQL_SELECT_BYIDPLAYER = "SELECT * FROM game WHERE id_player = ?";

	@Override
	public List<Game> findAll() throws DAOException {
		
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		Game game = null;
		
		List<Game> gameList = new ArrayList<>();
		
		try {
			connexion = this.factory.getConnection();
			statement = connexion.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL);
			while (resultSet.next()) {
				Player player =  this.factory.getPlayerDao().findById(resultSet.getLong("id_player"));
				game = map(resultSet, player);
				gameList.add(game);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, statement, connexion);
		}
		
		return gameList;
	}

	@Override
	public List<Game> findByPlayerId(Long idPlayer) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Game game = null;
		
		List<Game> gameList = new ArrayList<>();
		
		try {
			connexion = factory.getConnection();
			preparedStatement = initRequest(connexion, SQL_SELECT_BYIDPLAYER, false, idPlayer);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("coucou");
				Player player =  this.factory.getPlayerDao().findById(idPlayer);
				game = map(resultSet, player);
				gameList.add(game);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		
		return gameList;
	}
	
	
	// Transformation d'un résultat SQL en bean
		public static Game map(ResultSet result, Player player) throws SQLException {
			Game game = new Game();
			game.setId(result.getLong("id_game"));
			game.setPlayer(player);
			game.setScore(result.getInt("score"));
			game.setTime(result.getInt("time"));
			game.setDate(result.getTimestamp("date"));
			return game;
		}

}
