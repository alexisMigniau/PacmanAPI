package forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bean.Cosmetic;
import bean.Player;

public class CosmeticForm {
    private static final String CHAMP_id_cosmetic = "id_cosmetic";
    private static final String CHAMP_id_player = "id_player";
    private static final String CHAMP_price = "price";
    private static final String CHAMP_solde = "solde";

    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public List<Object> buyCosmeticValidateForm(HttpServletRequest request) {
        String idCosmetic = getValeurChamp(request, CHAMP_id_cosmetic);
        String idPlayer = getValeurChamp(request, CHAMP_id_player);
        String price = getValeurChamp(request, CHAMP_price);
        String solde = getValeurChamp(request, CHAMP_solde);

        List<Object> lstObjectRequest = new ArrayList<Object>();
        lstObjectRequest.add(idCosmetic);
        lstObjectRequest.add(idPlayer);
        lstObjectRequest.add(price);
        lstObjectRequest.add(solde);

        try {
            validationCapaciteAchat(Integer.parseInt(price), Integer.parseInt(solde));
        } catch (Exception e) {
            setErreur(CHAMP_id_cosmetic, e.getMessage());
        }
        if (erreurs.isEmpty()) {
            resultat = "Succès, l'achat a bien été effectué";
        } else {
            resultat = "Échec, l'achat n'a pas été effectué.";
        }

        return lstObjectRequest;
    }

    private void validationCapaciteAchat(int price, int solde) throws Exception {
        if (solde - price < 0) {
            throw new Exception("Vous n'avez plus assez de crédit !");
        }
    }

    private void validationNat(String nat) throws Exception {
        if (nat != null && nat.length() != 2) {
            throw new Exception("Le champ nationalité doit faire une taille de 2 caractères.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return "";
        } else {
            return valeur.trim();
        }
    }
}
