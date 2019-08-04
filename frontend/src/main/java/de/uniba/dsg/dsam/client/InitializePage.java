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
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

/**
 * 
 * @author GROUP 1
 * Servlet implementation class InitializePage.
 * <p>This servlet is initially called to supply information to customer order jsp page before its
 * load. It fetches the data like beverage manufacturer, name, availble quantity and incentive
 * associated with each beverage. Calling of business interface methods like display and getIncentive
 * is implemented to retreive required data. These methods are defined in BeverageManagementBean and 
 * IncentiveManagementBean respectively.</p>
 */
public class InitializePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitializePage.class.getName());
	@EJB
	BeverageManagement bm;
	
	@EJB
	IncentiveManagement im;
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		List <Beverage> list = bm.display();
		List<String> incentive_names = im.getIncentive();
		
		request.setAttribute("beveragelist", list);
		request.setAttribute("incentivenames", incentive_names);

		RequestDispatcher rd = request.getRequestDispatcher("CustomerBeverageDisp.jsp");
		rd.forward(request, response);
	}

}
