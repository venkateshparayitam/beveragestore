package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.NamedQuery;

import de.uniba.dsg.dsam.model.Beverage;


/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>Implementation of class OrderItemsEntity</p>
 */


@Entity
@NamedQuery(name="OrderItemsEntity.findAll",query="SELECT c.manufacturer, SUM(c.quantity) FROM OrderItemsEntity c group by c.manufacturer")
public class OrderItemsEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Version
	private int version;
	private String manufacturer;
    private String name;
    private int quantity;
    private double price;
    @Transient
    private List<Beverage> beverageItems;
    
   	@ManyToOne
    public CustomerOrderEntity customerorderentity;
    
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CustomerOrderEntity getCustomerorderentity() {
		return customerorderentity;
	}

	public void setCustomerorderentity(CustomerOrderEntity customerorderentity) {
		this.customerorderentity = customerorderentity;
	}

	@Transient
	 public List<Beverage> getBeverageItems() {
			return beverageItems;
		}

		public void setBeverageItems(List<Beverage> beverageItems) {
			this.beverageItems = beverageItems;
		}
    
}
