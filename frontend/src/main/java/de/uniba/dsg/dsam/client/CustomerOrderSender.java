package de.uniba.dsg.dsam.client;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import de.uniba.dsg.dsam.model.CustomerOrder;
/**
 * 
 * @author GROUP 1
 * CustomerOrderSender class implementation.
 * <p>This class containes the implementation of method queue sendMessage to send customer order
 * to the BeverageStoreQueue</p>
 */
@Stateless
public class CustomerOrderSender {
    private static final Logger logger = Logger.getLogger(CustomerOrderSender.class.getName());

    @Resource(mappedName = "BeverageStoreCF")
    private ConnectionFactory factory;

    @Resource(mappedName = "BeverageStoreQueue")
    private Queue target;
/**
 * <p>This method takes the customer order data and sends it to JMS queue named 'BeverageStoreQueue'
 * Session object is used to consume and produce messgaes. </p> 
 * @param order takes order of type CustomerOrder type.
 * @return void.
 */
    public void sendMessage(CustomerOrder order) {
        try (Connection connection = factory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(target);
            ObjectMessage message = session.createObjectMessage(order);
            producer.send(message);
            logger.info("Sent order to JMS queue");
        } catch (JMSException ex) {
            logger.severe("Could not send message to the Queue" + ex);
        }

    }
}