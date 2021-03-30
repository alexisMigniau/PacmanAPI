package dao;

import java.util.List;

import bean.Game;
import bean.Player;

public interface GameDao {

	// Connexion
	List<Game> findAll() throws DAOException;

	// Profil
	Player findByPlayerId(Long id) throws DAOException;
}
