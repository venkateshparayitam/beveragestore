package de.uniba.dsg.dsam.model;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is an abstarct class Incentive implementation.</p>
 */

public abstract class Incentive {
    private String name;
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
}
