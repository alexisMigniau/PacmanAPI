package controller;

import java.io.IOException;
import java.util.List;

import dao.DAOFactory;
import forms.CosmeticForm;
import dao.CosmeticDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Player;

@WebServlet("/cosmetics")
public class Cosmetics extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CosmeticDao cosmeticDao;

	public void init() throws ServletException {
		this.cosmeticDao = ((DAOFactory) getServletContext().getAttribute("daofactory")).getCosmeticDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		request.setAttribute("session", player);

		if (player == null) {
			request.setAttribute("listCosmeticsDisplay", cosmeticDao.findAll());
		} else {
			// If user is connected
			request.setAttribute("listCosmeticsDisplayPossessed",
					cosmeticDao.findAllCosmeticPossessedByPlayer(player.getId()));
			request.setAttribute("listCosmeticsDisplay", cosmeticDao.findAllCosmeticNotBuy(player.getId()));
			// System.out.println(cosmeticDao.findAllCosmeticNotBuy(player.getId()));
		}

		// System.out.println(player);

		this.getServletContext().getRequestDispatcher("/WEB-INF/displayCosmetics.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Création du formulaire
		CosmeticForm form = new CosmeticForm();
		List<Object> requestResult = form.cosmeticValidateForm(request);
		String selectActionToDoWithForm = form.getValeurChamp(request, "action_param");

		if (selectActionToDoWithForm.equals("buy")) {
			cosmeticDao.buyCosmetic(requestResult);
			cosmeticDao.debiteSolde(requestResult);
			System.out.println("passer dans buy avec succes");
		}
		if (selectActionToDoWithForm.equals("credit")) {
			// System.out.println("id_Player " + requestResult.get(1));
			// System.out.println("solde " + requestResult.get(3));

			cosmeticDao.crediterSolde(requestResult, 500);
		}

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		// J'ai mis en dur pour tester mais le mieux, c'est que ta fonction crediterSolde retourne le nouveau solde
		player.setSolde(player.getSolde()+500);
		
		response.sendRedirect("cosmetics");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
