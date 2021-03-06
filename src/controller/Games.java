package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Game;
import bean.Player;
import dao.DAOException;
import dao.DAOFactory;
import dao.GameDao;

/**
 * Servlet implementation class PlayerConnexion
 */
@WebServlet("/games")
public class Games extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private GameDao gameDao;
       	
	public void init() throws ServletException
	{
		this.gameDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getGameDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		bean.Player player = (bean.Player) session.getAttribute("player");
		System.out.println(session.getAttribute("player"));
		
		if (player != null) {			
			List<Game> gameList = gameDao.findByPlayerId(player.getId());
			
			request.setAttribute("session", player);
			request.setAttribute("games", gameList);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/displayGames.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		
		if(token != null && token.equals("8RCrv0rBNjpPPtTXvOTV"))
		{
			String login = request.getParameter("login");
			int score = Integer.parseInt(request.getParameter("score"));
			int rounds = Integer.parseInt(request.getParameter("rounds"));
			
			boolean added;
			try {
				gameDao.addGame(login, score, rounds);
				added = true;
			} catch(DAOException e) {
				added = false;
			}
			request.setAttribute("added", added);
		}else {
			request.setAttribute("added", false);
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSON/Game.jsp").forward( request, response );
	}

}
