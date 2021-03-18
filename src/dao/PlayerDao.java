package dao;

import java.util.List;

import bean.Player;

public interface PlayerDao {
	// Création d'un compte
	void ajouter(Player player) throws DAOException;
	
	// Connexion
	Player findByLogin(String login) throws DAOException;
	
	// Profil
	Player findById(Long id) throws DAOException;
	
	// Modifier un joueur
	boolean update(long id,Player player) throws DAOException;
	
	// Supprimer un joueur
	boolean delete(long id) throws DAOException;
}