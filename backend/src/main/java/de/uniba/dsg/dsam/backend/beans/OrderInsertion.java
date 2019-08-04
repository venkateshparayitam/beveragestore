package de.uniba.dsg.dsam.backend.beans;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.OrderItemsEntity;
import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;

/**
 * 
 * @author GROUP 1 WS2018/19
 * Session Bean implementation class OrderInsertion
 * <p>This class implements business interface method insertOrder to persist customer order
 * data in the database</p>
 */
@Stateless
@Local(OrderInsertionLocal.class)
public class OrderInsertion implements OrderInsertionLocal,Serializable {
	private static final Logger logger = Logger.getLogger(OrderInsertion.class.getName());

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)	
	EntityManager em;			
	
    /**
     * Default constructor. 
     */
    public OrderInsertion() {
    }
/**
 * <p>This method taked CustomerOrder object and implements the persistence of data object using
 * CustomerOrderEntity entity</p>
 * @param CustomerOrder
 * @return Nothing.
 */
	@Override
	public void insertOrder(CustomerOrder ord) {
		
		CustomerOrderEntity order = new CustomerOrderEntity();
		order.setIssuedate(ord.getIssueDate());
		order.setBeverageItems(ord.getOrderItems());
		
		for (int i = 0;i<ord.getOrderItems().size();i++)
		{
			OrderItemsEntity orderitemsentity = new OrderItemsEntity();
			orderitemsentity.setManufacturer(ord.getOrderItems().get(i).getManufacturer());
			orderitemsentity.setName(ord.getOrderItems().get(i).getName());
			orderitemsentity.setPrice(ord.getOrderItems().get(i).getPrice());
			orderitemsentity.setQuantity(ord.getOrderItems().get(i).getQuantity());
			em.persist(orderitemsentity);
			order.addbeverage(orderitemsentity);
			em.persist(order);
			Query query = em.createNamedQuery("BeverageEntity.UpdateQuantity",BeverageEntity.class).setParameter("orderedquantity", ord.getOrderItems().get(i).getQuantity()).setParameter("orderedmanufacturer", ord.getOrderItems().get(i).getManufacturer());
			int result = query.executeUpdate();
			logger.info("Result: "+result);
			
		}
		
		
		logger.info("Data written");
		
		
	}
	
	

}