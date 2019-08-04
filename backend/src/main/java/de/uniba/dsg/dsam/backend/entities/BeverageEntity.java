package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.NamedQuery;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;

import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;



/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>Implementation of class BeverageEntity</p>
 */

@Entity
@NamedQuery(name="getallbeverages",
					query="SELECT b FROM BeverageEntity b")
@NamedQuery(name="getbeverageincentiveid",
					query="SELECT b FROM BeverageEntity b WHERE b.name = :n")
@NamedQuery(name="updatequantity", 
					query="UPDATE BeverageEntity be SET be.quantity = :q WHERE be.name = :n")
@NamedQuery(name="deletebeverage",
					query="DELETE FROM BeverageEntity WHERE name = :n")
@NamedQuery(name="BeverageEntity.findAll",query="SELECT b FROM BeverageEntity b")
@NamedQuery(name="BeverageEntity.UpdateQuantity",query="UPDATE BeverageEntity b SET b.quantity = b.quantity - :orderedquantity WHERE b.manufacturer = :orderedmanufacturer")

public class BeverageEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Version
	private int version;

	private String manufacturer;
	private String name;
	private int quantity;
	private double price;
	
	@ManyToOne
	private IncentiveEntity incentiveentity;
	
	public void setManufacturer(String m) {
		this.manufacturer = m;
	}
	public void setName(String n) {
		this.name = n;
	}
	public void setQuantity(int q) {
		this.quantity = q;
	}
	public void setPrice(double p) {
		this.price = p;
	}
	public void setIncentiveEntity(IncentiveEntity ie) {
		this.incentiveentity = ie;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}
	public String getName() {
		return this.name;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public double getPrice() {
		return this.price;
	}
	public IncentiveEntity getIncentiveEntity() {
		return this.incentiveentity;
	}
	
	public int getId() {
		return this.id;
	}
}
