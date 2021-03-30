package controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Game;
import bean.PlayerRank;
import bean.RankingList;
import dao.DAOFactory;
import dao.GameDao;

/**
 * Servlet implementation class PlayerConnexion
 */
@WebServlet("/ranking")
public class Ranking extends HttpServlet {
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
		
		List<Game> gameList = gameDao.findAll();
		
		RankingList rankingList = new RankingList();
		bean.Player player = null;
		
		for(Game game : gameList) {
			player = game.getPlayer();
			if (!rankingList.isPlayerInRankingList(player.getId())) {
				PlayerRank playerRank = new PlayerRank(player.getId(), player.getPseudo(), game.getScore(), player.getNationality());
				rankingList.addPlayerRank(playerRank);
			} else {
				rankingList.modifyPlayerRankTotalRank(player.getId(), game.getScore());
			}
		}
		
		Collections.sort(rankingList.getRankingList());
		request.setAttribute("rankingList", rankingList.getRankingList());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/displayRanking.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/displayRanking.jsp").forward( request, response );
	}

}
