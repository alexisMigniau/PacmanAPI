package controller;

import java.io.IOException;
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
 * Servlet implementation class PlayerConnexion
 */
@WebServlet("/connexion")
public class PlayerConnexion extends HttpServlet {
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
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/formConnexionPlayer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Création du formulaire
		PlayerForm form = new PlayerForm();
		
		Player player = form.connexionPlayer(request);
		
		player = playerDao.findByLogin(player.getLogin(), player.getPassword());
		
		// Si on ne trouve pas de joueur alors on renvoie une erreur, et on renvoie sur la page de connexion
		if(player == null)
		{
			request.setAttribute("erreur", true);
			this.getServletContext().getRequestDispatcher("/WEB-INF/formConnexionPlayer.jsp").forward( request, response );
		}
		// Si on trouve un joueur, alors on définit les variables de session, et on renvoie sur la page d'accueil
		else {
			player.setPassword("");
			HttpSession session = request.getSession();
			session.setAttribute("player", player);
			
			response.sendRedirect(request.getContextPath());
		}
	}

}
