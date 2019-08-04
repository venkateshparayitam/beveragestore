package de.uniba.dsg.dsam.model;

import java.io.Serializable;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is a class Beverage implementation.</p>
 */
public class Beverage implements Serializable{
    private String manufacturer;
    private String name;
    private int quantity;
    private double price;

    private Incentive incentive;
    
    //private Incentive incentive;
    public Beverage() {
    	
    }

	public String getManufacturer() {
		return manufacturer;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public Incentive getIncentive() {
		return this.incentive;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setIncentive(Incentive i) {
		this.incentive = i;
	}
	   
}
