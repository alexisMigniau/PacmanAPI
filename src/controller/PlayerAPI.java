package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.DAOFactory;
import dao.PlayerDao;

/**
 * Servlet implementation class Player
 */
@WebServlet("/player")
public class PlayerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
private PlayerDao playerDao;
	
	public void init() throws ServletException
	{
		this.playerDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getPlayerDao();
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * Requete de connexion en API Rest
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Récupération des paramètres via le get
		String login = request.getParameter("login");
		String password = request.getParameter("false");
		
		bean.Player player = playerDao.findByLogin(login, password);
		
		String message;
		boolean result;
		// Si on ne trouve pas de joueur alors on renvoie une erreur, e
		if(player == null)
		{
			message = "Aucun compte trouver avec ce couple login password";
			result = false;
		}
		// Si on trouve un joueur, alors on définit les variables de session
		else {
			player.setPassword("");
			HttpSession session = request.getSession();
			session.setAttribute("player", player);
			

			message = "Connexion réussie";
			result = true;
		}
		
		// Parse to JSON Format
		String json = "{ \"resultat\" : \""+ result + "\", \"message\" : \"" + message + "\"}";
		
		// On retourne les données au format JSON
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

}
