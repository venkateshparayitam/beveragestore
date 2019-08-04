package de.uniba.dsg.dsam.backend.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Version;


/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>Implementation of class TrialPackageEntity</p>
 */
@Entity
public class TrialPackageEntity extends IncentiveEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	@Version
	private int version;
	
	private String description;
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}
}
