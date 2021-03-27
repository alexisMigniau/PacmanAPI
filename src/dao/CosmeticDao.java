package dao;

import java.util.List;

import bean.Cosmetic;

public interface CosmeticDao {

		// Fetcher tous les cosmétiques
		List<Cosmetic> findAll() throws DAOException;

		// Ajoute un cosmetic dans la table intermediaire cosmetic/player
		Cosmetic buyCosmetic(List<Object> requestResult) throws DAOException;

		// Debite le solde de player
		Cosmetic debiteSolde(List<Object> requestResult) throws DAOException;
		// Modifier un cosmetic
		boolean update(long id, Cosmetic cosmetic) throws DAOException;
		
		// Supprimer un joueur
		boolean delete(long id) throws DAOException;
	}

