package de.uniba.dsg.dsam.model;

import java.io.Serializable;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is a class BIReport implementation.</p>
 */

public class BIReport implements Serializable{
    private String manufacturer;
    private int quantity;
    
    public BIReport() {
    	
    }

    public String getManufacturer() {
		return manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
