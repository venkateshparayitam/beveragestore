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


import de.uniba.dsg.dsam.model.BIReport;
import de.uniba.dsg.dsam.persistence.SalesManagement;


/**
 * 
 * @author GROUP 1
 * Servlet implementation class BIReportServlet.
 * <p>This servlet gets the data from business interface method getReport where implemented in 
 * SalesManagementBean class. Once business data like number of beverages sold is retrieved in the
 * form of list then it is forwarded to BIReport.jsp.</p>
 */
public class BIReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(InitializePage.class.getName());
	@EJB
	SalesManagement sm;   
    /**
     * @see HttpServlet#HttpServlet()
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <BIReport> list = sm.getReport();
		request.setAttribute("bireportlist", list);
		RequestDispatcher rd = request.getRequestDispatcher("BIReport.jsp");
		rd.forward(request, response);
	}

}
