package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Player;
import dao.DAOFactory;
import dao.PlayerDao;
import forms.PlayerForm;

/**
 * Servlet implementation class PlayerProfile
 */
@WebServlet("/profile")
public class PlayerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private PlayerDao playerDao;
	
	public void init() throws ServletException
	{
		this.playerDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getPlayerDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la page profil
		// Si il y a le paramètre id alors on affiche le profil de cette personne, sinon on affiche la page de profil du joueur connectée
		String idParam = request.getParameter("idPlayer");
		
		Long idPlayer;
		// Si on affiche la page du joueur connectée alors les champs sont modifiables
		boolean editable = false;
		
		if(idParam == null)
		{
			HttpSession session = request.getSession();
			Player playerSession = (Player) session.getAttribute("player");
			editable = true;
			if(playerSession == null)
				idPlayer = (long) 0;
			else 
				idPlayer = playerSession.getId();
		} else
		{
			idPlayer = Long.parseLong(idParam);
		}
			
		Player player = playerDao.findById(idPlayer);
		
		request.setAttribute("profile", player);
		request.setAttribute("editable", editable);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Long id_player = ((Player)session.getAttribute("player")).getId();
		
		PlayerForm form = new PlayerForm();
		
		// Récupération du bean
		Player new_player = form.updatePlayer(request);
		new_player.setId(id_player);
		
		// Si il n'y a pas d'erreur alors on update la BDD
		if(form.getErreurs().isEmpty())
			playerDao.update(new_player);
		
		// On onlève le mot de passe pour ne pas le stocker dans la variable de session
		new_player.setPassword(null);
		// Update des variables de session
		session.setAttribute("player", new_player);
		
		// Stockage du formulaire pour récupérer le résultat et les erreurs
		request.setAttribute("form", form);
		request.setAttribute("profile", new_player);
		request.setAttribute("editable", true);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/profile.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'id du joueur à supprimé depuis la variable de session
		HttpSession session = request.getSession();
		Long id_player = ((Player)session.getAttribute("player")).getId();
		
		playerDao.delete(id_player);
		
		// On supprime les variables de session car on déconnecte le joueur après la suppression de son compte
		request.getSession().invalidate();
		request.logout();
		
		// Parse to JSON Format
		String json = "{ \"resultat\" : " + true + ", \"message\" : \"Le compte à bien été supprimé, redirection sur la page d'accueil\"}";
		
		// On retourne les données au format JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
