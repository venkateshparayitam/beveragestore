package de.uniba.dsg.dsam.model;

import java.io.Serializable;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is a class TrialPackage implementation.</p>
 */

public class TrialPackage extends Incentive implements Serializable {
	private String description;
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public String getDescription() {
		return this.description;
	}
}
