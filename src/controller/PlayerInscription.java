package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Player;
import dao.DAOFactory;
import dao.PlayerDao;
import forms.InscriptionForm;

/**
 * Servlet implementation class Players
 */
@WebServlet("/inscription")
public class PlayerInscription extends HttpServlet {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/formInscriptionPlayer.jsp").forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Création du formulaire
		InscriptionForm form = new InscriptionForm();
		
		// Récupération du bean
		Player player = form.inscrirePlayer(request);
		
		playerDao.ajouter(player);
		
		// Stockage du formulaire pour récupérer le résultat et les erreurs
		request.setAttribute("form", form);
		request.setAttribute("utilisateur", player);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/formInscriptionPlayer.jsp").forward( request, response );
	}
}
