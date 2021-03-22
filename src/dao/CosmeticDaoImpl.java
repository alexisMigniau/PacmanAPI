package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static dao.DAOUtil.*;

import bean.Cosmetic;
import bean.Player;

public class CosmeticDaoImpl implements CosmeticDao {
	
	private DAOFactory factory;	
	
	public CosmeticDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String SQL_INSERT = "INSERT INTO `player` (`id_player`, `pseudo`, `login`, `password`, `nationality`, `date_inscription`) VALUES (NULL, ?, ?, MD5(?), ?, CURRENT_TIMESTAMP)";
	
	@Override
	public void ajouter(Cosmetic cosmetic) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet valeursAutoGenerees = null;

	    try {
	        connexion = factory.getConnection();
	        preparedStatement = initRequest( connexion, SQL_INSERT, true, cosmetic.getName(), cosmetic.getPrice());
	        int statut = preparedStatement.executeUpdate();
	        /* Analyse du statut retourné par la requête d'insertion */
	        if ( statut == 0 ) {
	            throw new DAOException( "Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table." );
	        }
	        /* Récupération de l'id auto-généré par la requête d'insertion */
	        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
	        if ( valeursAutoGenerees.next() ) {
	            /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
	            cosmetic.setId( valeursAutoGenerees.getLong( 1 ) );
	        } else {
	            throw new DAOException( "Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné." );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( valeursAutoGenerees, preparedStatement, connexion );
	    }
	}

	private static final String SQL_SELECT_BY_LOGIN = "SELECT id_cosmetic, name, price FROM cosmetic WHERE name = ?";
	
	@Override
	public Cosmetic findByName(String name) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Cosmetic cosmetic = null;
	    
	    try {
	        // Ouverture de la connexion
	        connexion = factory.getConnection();
	        preparedStatement = initRequest( connexion, SQL_SELECT_BY_LOGIN, false, name );
	        resultSet = preparedStatement.executeQuery();
	   
	        if ( resultSet.next() ) {
	        	cosmetic = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	   
		return cosmetic;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT id_player, pseudo, login, password, nationality, date_inscription FROM player WHERE id_player = ?";
	
	@Override
	public Cosmetic findById(Long id) throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Cosmetic cosmetic = null;
	    
	    try {
	        // Ouverture de la connexion
	        connexion = factory.getConnection();
	        preparedStatement = initRequest( connexion, SQL_SELECT_BY_ID, false, id );
	        resultSet = preparedStatement.executeQuery();
	   
	        if ( resultSet.next() ) {
	        	cosmetic = map( resultSet );
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	   
		return cosmetic;
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
