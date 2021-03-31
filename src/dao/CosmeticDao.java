package dao;

import java.util.List;

import bean.Cosmetic;

public interface CosmeticDao {

	// Fetcher tous les cosmétiques
	List<Cosmetic> findAll() throws DAOException;

	// Fetcher tous les cosmétiques non possedé par le joueur
	List<Cosmetic> findAllCosmeticNotBuy(Long id_player) throws DAOException;

	// Fetcher tous les cosmétiques que possède le joueur
	List<Cosmetic> findAllCosmeticPossessedByPlayer(Long id_player) throws DAOException;

	// Ajoute un cosmetic dans la table intermediaire cosmetic/player
	Cosmetic buyCosmetic(List<Object> requestResult) throws DAOException;

	// Debite le solde de player
	int debiteSolde(List<Object> requestResult) throws DAOException;

	// Crediter le solde de player
	int crediterSolde(List<Object> requestResult, int amount);

	// Equiper le joueur avec un cosmetic
	void equipCosmetic(List<Object> requestResult);

	// Modifier un cosmetic
	boolean update(long id, Cosmetic cosmetic) throws DAOException;

	// Supprimer un joueur
	boolean delete(long id) throws DAOException;
}
