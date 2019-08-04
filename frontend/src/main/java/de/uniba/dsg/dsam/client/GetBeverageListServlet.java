package de.uniba.dsg.dsam.client;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.util.logging.Logger;


import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;


/**
 * 
 * @author GROUP 1
 * Servlet implementation class GetBeverageListServlet.
 * <p>This servlet is initially called to supply information to salesman jsp page before its
 * load. It fetches the beverage name. Calling of business interface methods like display to retreive required data.
 *  This methods are defined in BeverageManagementBean.</p>
 */
public class GetBeverageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitializePage.class.getName());
	@EJB
	BeverageManagement bm;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List <Beverage> list = bm.display();
		
		request.setAttribute("beveragelist", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("salesman.jsp");
		rd.forward(request, response);
	}

}
