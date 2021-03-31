package forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bean.Player;

public class PlayerForm {
	private static final String CHAMP_LOGIN 	= "login";
	private static final String CHAMP_PSEUDO 	= "pseudo";
    private static final String CHAMP_PASS 		= "motdepasse";
    private static final String CHAMP_CONF 		= "confirmation";
    private static final String CHAMP_NAT 		= "nationalite";
    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public Player inscrirePlayer( HttpServletRequest request ) {
        String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String nat = getValeurChamp( request, CHAMP_NAT );

        Player player = new Player();
        
        try {
            validationMotsDePasse( motDePasse, confirmation );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
            setErreur( CHAMP_CONF, null );
        }
        player.setPassword(motDePasse);

        try {
            validationLogin( login );
        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        player.setLogin(login);
        
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        player.setPseudo(pseudo);
        
        try {
            validationNat( nat );
        } catch ( Exception e ) {
            setErreur( CHAMP_NAT, e.getMessage() );
        }
        player.setNationality(nat);
        
        if ( erreurs.isEmpty() ) {
            resultat = "Succès de l'inscription. Vous pouvez vous connecter via la page de connexion";
        } else {
            resultat = "Échec de l'inscription.";
        }

        return player;
    }
    
    public Player connexionPlayer(HttpServletRequest request)
    {
    	 String login = getValeurChamp( request, CHAMP_LOGIN );
    	 String motDePasse = getValeurChamp( request, CHAMP_PASS );
    	 
    	 Player player = new Player();
    	 
    	 player.setPassword(motDePasse);
         player.setLogin(login);
         
         return player;
    }
    
    public Player updatePlayer(HttpServletRequest request) {
    	String login = getValeurChamp( request, CHAMP_LOGIN );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );
        String confirmation = getValeurChamp( request, CHAMP_CONF );
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String nat = getValeurChamp( request, CHAMP_NAT );
        
        Player player = new Player();
         
        if(motDePasse != "" || confirmation != "")
        {
	        try {
	            validationMotsDePasse( motDePasse, confirmation );
	        } catch ( Exception e ) {
	            setErreur( CHAMP_PASS, e.getMessage() );
	            setErreur( CHAMP_CONF, null );
	        }
	        player.setPassword(motDePasse);
        } else {
        	player.setPassword(null);
        }

        try {
            validationLogin( login );
        } catch ( Exception e ) {
            setErreur( CHAMP_LOGIN, e.getMessage() );
        }
        player.setLogin(login);
        
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        player.setPseudo(pseudo);
        
        try {
            validationNat( nat );
        } catch ( Exception e ) {
            setErreur( CHAMP_NAT, e.getMessage() );
        }
        player.setNationality(nat);
        
        if ( erreurs.isEmpty() ) {
            resultat = "Les informations ont bien été changées";
        } else {
            resultat = "Échec du changement des informations";
        }
        
		return player;
	}
    
    private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception {
        if ( motDePasse != null && confirmation != null ) {
            if ( !motDePasse.equals( confirmation ) ) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if ( motDePasse.length() < 8 ) {
                throw new Exception( "Les mots de passe doivent contenir au moins 8 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir et confirmer votre mot de passe." );
        }
    }

    private void validationLogin( String login ) throws Exception {
        if ( login != null && login.length() < 6 ) {
            throw new Exception( "Le login doit contenir au moins 6 caractères." );
        }
    }
    
    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo != null && pseudo.length() < 4 ) {
            throw new Exception( "Le pseudo doit contenir au moins 4 caractères." );
        }
    }
    
    private void validationNat( String nat ) throws Exception {
        if ( nat != null && nat.length() != 2 ) {
            throw new Exception( "Le champ nationalité doit faire une taille de 2 caractères." );
        }
    }
    
    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return "";
        } else {
            return valeur.trim();
        }
    }
}
