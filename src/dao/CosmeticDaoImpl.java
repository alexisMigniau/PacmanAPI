package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.DAOUtil.*;

import bean.Cosmetic;
import bean.Player;

public class CosmeticDaoImpl implements CosmeticDao {
	
	private DAOFactory factory;	
	
	public CosmeticDaoImpl(DAOFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String SQL_SELECT_BY_LOGIN = "SELECT id_cosmetic, name, price FROM cosmetic WHERE name = ?";
	
	//Verifier que le joueur n a pas deja la cosmetic en question
	//AJouter une ligne dans player_cometic
	//Debiter le solde du joueur
	@Override
	public Cosmetic buyCosmetic(String name) throws DAOException {
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

	private static final String SQL_SELECT_ALL = "SELECT id_cosmetic, name, price FROM cosmetic";
	
	@Override
	public List<Cosmetic> findAll() throws DAOException {
		Connection connexion = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Cosmetic cosmetic = null;
	    List<Cosmetic> listCosmetic = new ArrayList<Cosmetic>() ;;
	    try {
	        // Ouverture de la connexion
	        connexion = factory.getConnection();
	        preparedStatement = initRequest(connexion, SQL_SELECT_ALL, false);
	        resultSet = preparedStatement.executeQuery();
	        while ( resultSet.next() ) {
	        	cosmetic = map( resultSet );
		        listCosmetic.add(cosmetic);      	
	        }
	    } catch ( SQLException e ) {
	        throw new DAOException( e );
	    } finally {
	        fermeturesSilencieuses( resultSet, preparedStatement, connexion );
	    }
	   
		return listCosmetic;
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
