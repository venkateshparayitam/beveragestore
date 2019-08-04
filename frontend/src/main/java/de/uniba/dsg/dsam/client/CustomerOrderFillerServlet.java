package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import javax.ejb.EJB;

import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.model.Beverage;

/**
 * 
 * @author GROUP 1
 * Servlet implementation class CustomerOrderFillerServlet.
 * <p>This servlet gets the customer order data from Customer and sends it to JMS queue 
 * for further processing. </p>
 */

public class CustomerOrderFillerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CustomerOrderFillerServlet.class.getName());
    
	@EJB
	CustomerOrderSender sender;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {			
			
			 List<Beverage> list_beverage = new ArrayList<Beverage>();
			 Date issuedate = new Date();
			 String[] checkeditems =request.getParameterValues("checkbox");
		
			 for(int i = 0;i<checkeditems.length;i++)
			 {
				 StringTokenizer st = new StringTokenizer(checkeditems[i],",");
				 	 
				 	 Beverage obj_beverage = new Beverage();
				 	 obj_beverage.setManufacturer(st.nextToken());
				 	 obj_beverage.setName(st.nextToken());
				 	 obj_beverage.setPrice(Double.parseDouble(st.nextToken()));
				 	 obj_beverage.setQuantity(Integer.parseInt(request.getParameter("beverage_qty_"+st.nextToken())));
					 
				 	list_beverage.add(obj_beverage);
			 }

			
			CustomerOrder order = new CustomerOrder();
			order.setOrderItems(list_beverage);
			order.setIssueDate(issuedate);

			sender.sendMessage(order);
			
		RequestDispatcher rd = request.getRequestDispatcher("Done.jsp");
		rd.forward(request, response);
		
		} catch(NumberFormatException e) {
			logger.severe("Invalid data" + e);
		}

	}

}
