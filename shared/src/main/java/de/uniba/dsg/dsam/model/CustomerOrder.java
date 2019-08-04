package de.uniba.dsg.dsam.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is a class CustomerOrder implementation.</p>
 */

public class CustomerOrder implements Serializable {

	
    private Date issueDate;
    private List<Beverage> orderItems;
  
    public CustomerOrder(){
		orderItems = new ArrayList<Beverage>();
		issueDate = new Date();
	}
    
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public List<Beverage> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Beverage> orderItems) {
		this.orderItems = orderItems;
	}

	

	
}
