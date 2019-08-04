package de.uniba.dsg.dsam.backend.beans;

import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * OrderMessageDrivenBean class implementation.
 * <p>This class implements the retrieval of Customer order message from queue and sends it to 
 * business interface method inserOrder to persist the order data into the database.</p> 
 */

@MessageDriven( mappedName="BeverageStoreQueue",
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue"
		) })

public class OrderMessageDrivenBean implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(OrderMessageDrivenBean.class.getName());
	
	@EJB
	OrderInsertionLocal oil;
	
	public OrderMessageDrivenBean() {}

	/**
	 * <p>This method takes the parameter of type Message which contains CutstomerOrder as a data
	 * and calls the business interface insertOrder of interface OrderInsertionLocal.</p>
	 * @param Message --Queue Message type that encapsulates CustomerOrder type data object.
	 * @returns void
	 */
    public void onMessage(Message message) {
    	
    	if(message == null){
    		logger.warning("Received null message via Beverage Store queue");
    		return;
    	}
    	
    	 if(message instanceof ObjectMessage){
         	try {
 				Object o = ((ObjectMessage)message).getObject();
 				logger.info("Received data");
 				oil.insertOrder((CustomerOrder)o);
 				logger.info("Done data");
         	}catch (JMSException e) {
				logger.severe("Error in accessing object" + e);
         	}
    	 }
}}