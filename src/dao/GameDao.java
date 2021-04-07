package dao;

import java.util.List;

import bean.Game;
import bean.Player;

public interface GameDao {

	// Récupérer toutes les parties
	List<Game> findAll() throws DAOException;

	// Récupérer les parties d'un joueur précis
	List<Game> findByPlayerId(Long idPlayer) throws DAOException;
	
	void addGame(String login, int score, int time) throws DAOException;
}
