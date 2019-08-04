package de.uniba.dsg.dsam.backend.beans;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import de.uniba.dsg.dsam.model.BIReport;
import javax.ejb.Remote;
import de.uniba.dsg.dsam.backend.entities.OrderItemsEntity;
import java.util.logging.Logger;
import de.uniba.dsg.dsam.persistence.SalesManagement;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * Session Bean implementation class SalesManagementBean
 * <p>This class implements business interface method getReport to fetch all the beverage data for
 * the Business Intelligence report.</p>
 */
@Stateless
@Remote(SalesManagement.class)
public class SalesManagementBean implements SalesManagement {
	private static final Logger logger = Logger.getLogger(OrderMessageDrivenBean.class.getName());
	
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;
	
	/**
	 * <p>This method implements and allows to get the manufacturer and the sold quantity for the 
	 * generation of BI report. This returns all the data from OrderItemsEntity table / entity</p>
	 * @param Nothing
	 * @return List<BIReport>
	 */
    @Override
    public List<BIReport> getReport() {
    	List <BIReport> bireport_list = new ArrayList<BIReport>();
    	BIReport bireport; 
    	//@SuppressWarnings("unchecked")
    	Query query = em.createNamedQuery("OrderItemsEntity.findAll",OrderItemsEntity.class);
    	List<Object[]> list1 = query.getResultList();
    	for( Object[] be:list1 ) {
    		bireport = new BIReport();
    		bireport.setManufacturer((String)be[0]);
    		bireport.setQuantity(((Number)be[1]).intValue());
            
    		bireport_list.add(bireport);
    	}
    	em.flush();
    	
    	logger.info("Report: "+bireport_list.size());
    	return bireport_list;
    }
}
