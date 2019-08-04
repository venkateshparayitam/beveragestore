package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.Local;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * Simple interface for a session bean that is not to be exposed to the outside world.
 * 
 * @author GROUP 1 WS2018/19.
 *
 */
public interface OrderInsertionLocal {

	/**
	 * Adds a new order to the database.
	 * 
	 * @param orders
	 */
	public void insertOrder(CustomerOrder rrd);
}