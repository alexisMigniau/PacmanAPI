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

		String idCosmetic = request.getParameter("id_cosmetic");
		String idCosmeticss = request.getParameter("id_cosmeticss");
		String idCosmeticsss = request.getParameter("id_cosmeticsss");
		String idPlayer = request.getParameter("id_player");
		String price = request.getParameter("price");
		String solde = request.getParameter("solde");

		// System.out.println("-idCosmetic : " + idCosmetic);
		// System.out.println("-idPlayer : " + idPlayer);
		// System.out.println("-price : " + price);
		// System.out.println("-solde : " + solde);

		List<Object> requestResult = form.cosmeticValidateForm(request);
		String selectActionToDoWithForm = form.getValeurChamp(request, "action_param");
		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");
		if (selectActionToDoWithForm.equals("buy")) {
			cosmeticDao.buyCosmetic(requestResult);
			player.setSolde(cosmeticDao.debiteSolde(requestResult));
			System.out.println("passer dans buy avec succes");
		}
		if (selectActionToDoWithForm.equals("credit")) {
			// System.out.println("id_Player " + requestResult.get(1));
			// System.out.println("solde " + requestResult.get(3));

			player.setSolde(cosmeticDao.crediterSolde(requestResult, 500));
		}
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
