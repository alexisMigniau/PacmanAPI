package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import bean.Player;

public class PlayerDaoImpl implements PlayerDao {
	
	private DAOFactory factory;	
	
	public PlayerDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public void ajouter(Player player) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Player findByLogin(String login) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player findById(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Player> getClassement(String nationality) throws DAOException {
		// TODO Auto-generated method stub
		return null;
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
		player.setLogin(result.getString("login"));
		player.setNationality(result.getString("nationality"));
		player.setDateInscription(result.getTimestamp( "date_inscription" ));
		return player;
	}
}
