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
			request.setAttribute("cosmeticEquiped", cosmeticDao.getCosmeticEquiped(player.getId()));
		}

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

		HttpSession session = request.getSession();
		Player player = (Player) session.getAttribute("player");

		List<Object> requestResult = form.cosmeticValidateForm(request, player);
		String selectActionToDoWithForm = form.getValeurChamp(request, "action_param");

		if (selectActionToDoWithForm.equals("buy")) {
			cosmeticDao.buyCosmetic(requestResult);
			player.setSolde(cosmeticDao.debiteSolde(requestResult));
		}
		if (selectActionToDoWithForm.equals("credit")) {
			player.setSolde(cosmeticDao.crediterSolde(requestResult, 500));
		}
		if (selectActionToDoWithForm.equals("equip")) {
			cosmeticDao.equipCosmetic(requestResult);

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
