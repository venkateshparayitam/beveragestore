package de.uniba.dsg.dsam.client;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;

import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;

/**
 * 
 * @author GROUP 1 WS2018/19
 * BeverageServlet class handles UI interactions
 * <p>
 * BeverageServlet class handles the UI interaction operations for creating, deleting and updating
 * the beverages using BeverageManagement interface.
 * </p>
 *
 */
public class BeveragesServlet extends HttpServlet {
	
	@EJB
	BeverageManagement bm;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Beverage bc = new Beverage();
		Beverage bu = new Beverage();
		Beverage bd = new Beverage();
		
		String incentiveType = req.getParameter("itype");
	
		Incentive ipg = new PromotionalGift();
		Incentive itp = new TrialPackage();
		
		if(incentiveType.equals("gift")) {
			PromotionalGift pg = (PromotionalGift)ipg;
			pg.setName(req.getParameter("iname"));
			pg.setDescription(req.getParameter("idesc"));
		}
		else if(incentiveType.equals("package")) {
			TrialPackage tp = (TrialPackage)itp;
			tp.setName(req.getParameter("iname"));
			tp.setDescription(req.getParameter("idesc"));
		}
		
		List<Beverage> beverages = new ArrayList<Beverage>();
		
		PrintWriter pw = res.getWriter();
		if(req.getParameter("create") != null) {
			bc.setManufacturer(req.getParameter("manufacturer"));
			bc.setName(req.getParameter("beverageName"));
			bc.setQuantity(Integer.parseInt(req.getParameter("quantity")));
			bc.setPrice(Double.parseDouble(req.getParameter("price")));
			if(incentiveType.equals("gift")) {
				bc.setIncentive(ipg);
			}
			else if(incentiveType.equals("package")) {
				bc.setIncentive(itp);
			}
			
			bm.create(bc);
			pw.println("Beverage with following details added successfully:");
			pw.println("Manufaturer: " + bc.getManufacturer());
			pw.println("Name: " + bc.getName());
			pw.println("Quantity: " + bc.getQuantity());
			pw.println("Price: " + bc.getPrice());
			if(incentiveType.equals("gift")) {
				pw.println("Incentive - Promotional Gift : " + ipg.getName());
			}
			else if(incentiveType.equals("package")) {
				pw.println("Incentive - Trial Package: " + itp.getName());
			}
			
		}
		if(req.getParameter("update") != null) {
			bu.setName(req.getParameter("beverage_update"));
			bu.setQuantity(Integer.parseInt(req.getParameter("quantityUpdate")));
			bm.update(bu);
			pw.println(bu.getName() + " beverage updated successfully with new quantity" + bu.getQuantity());
		}
		if(req.getParameter("delete") != null) {
			bd.setName(req.getParameter("beverage_delete"));
			bm.delete(bd);
			pw.println("Beverage " + bd.getName() + " deleted successfully");
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

	}
}
