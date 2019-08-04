package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.NamedQuery;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;


/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>Implementation of class IncentiveEntity</p>
 */

@Entity
@NamedQuery(name="IncentiveEntity.findAll",query="SELECT i.name FROM IncentiveEntity i")
@NamedQuery(name="deleteincentive",
query="DELETE FROM IncentiveEntity WHERE id = :n")
								
public abstract class IncentiveEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Version
	private int version;
	
	private String name;
	
	@OneToMany
	private List<BeverageEntity> beverageentity; 
	    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public int getId() {
    	return this.id;
    }
}
