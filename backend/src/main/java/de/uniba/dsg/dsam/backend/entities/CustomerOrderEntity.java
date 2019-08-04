package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;
import javax.persistence.Version;


import de.uniba.dsg.dsam.model.Beverage;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>Implementation of class CustomerOrderEntity</p>
 */

@Entity
public class CustomerOrderEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Version
	private int version;
	@javax.persistence.Temporal(TemporalType.DATE)
	private Date issuedate;
	
	@OneToMany(mappedBy="customerorderentity")
	private List<OrderItemsEntity> orderItems;
	
	private List<Beverage> beverageItems;
	
	public CustomerOrderEntity()
	{
		orderItems = new ArrayList<OrderItemsEntity>();
	}
	public List<Beverage> getBeverageItems() {
		return beverageItems;
	}
	public void setBeverageItems(List<Beverage> beverageItems) {
		this.beverageItems = beverageItems;
	}
	public Date getIssuedate() {
		return issuedate;
	}
	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}
	public void addbeverage(OrderItemsEntity beverage) {
        if (!getOrderItems().contains(beverage)) {
            getOrderItems().add(beverage);
            if (beverage.getCustomerorderentity() != null) {
            	beverage.getCustomerorderentity().getOrderItems().remove(beverage);
            }
            beverage.setCustomerorderentity(this);
        }
    }
	public List<OrderItemsEntity> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemsEntity> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
	
	
	
}
