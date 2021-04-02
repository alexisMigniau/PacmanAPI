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

@WebServlet("/currentCosmetic")
public class CurrentCosmetic extends HttpServlet {
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
		String token = request.getParameter("token");
		if(token.equals("8RCrv0rBNjpPPtTXvOTV")){
			String loginplayer = request.getParameter("login");
			//currentCosmetic?token=X&login=X
			request.setAttribute("getColorCosmeticEquiped", cosmeticDao.getColorCosmeticEquiped(loginplayer));
			this.getServletContext().getRequestDispatcher("/WEB-INF/JSON/CurrentCosmetic.jsp").forward( request, response );
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
