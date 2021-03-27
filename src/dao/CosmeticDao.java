package dao;

import java.util.List;

import bean.Cosmetic;

public interface CosmeticDao {

		// Fetcher tous les cosmétiques
		List<Cosmetic> findAll() throws DAOException;

		// Ajoute un cosmetic dans la table intermediaire cosmetic/player et debite le solde de player
		Cosmetic buyCosmetic(String name) throws DAOException;

		// Modifier un cosmetic
		boolean update(long id, Cosmetic cosmetic) throws DAOException;
		
		// Supprimer un joueur
		boolean delete(long id) throws DAOException;
	}

