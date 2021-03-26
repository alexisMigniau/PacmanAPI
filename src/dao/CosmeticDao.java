package dao;

import java.util.List;

import bean.Cosmetic;

public interface CosmeticDao {

		// Création d'une cosmetique
		void ajouter(Cosmetic cosmetic) throws DAOException;
		
		// Fetcher tous les cosmétiques
		Cosmetic findAll() throws DAOException;

		// Trouver cosmetique par id
		Cosmetic findById(Long id) throws DAOException;
		
		// Trouver cosmetique par nom
		Cosmetic findByName(String name) throws DAOException;

		// Modifier un joueurformPlayer
		boolean update(long id, Cosmetic cosmetic) throws DAOException;
		
		// Supprimer un joueur
		boolean delete(long id) throws DAOException;
	}

