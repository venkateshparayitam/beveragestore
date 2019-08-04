package de.uniba.dsg.dsam.backend.beans;
import java.util.List;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;
import javax.ejb.Remote;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.ejb.Stateless;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;

/**
 * 
 * @author GROUP 1 WS2018/19
 * IncentiveManagementBean implements IncentiveManagement interface
 * <p>
 * IncentiveManagementBean implements IncentiveManagement interface.
 * </p>
 */
@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;
	
	/**
	 * getIncentive method retrieves the list of incentives
	 * <p>
	 * getIncentive method retrieves the list of incentives from incentiveentity table
	 * </p>
	 * @param None.
	 * @return List<String>
	 */
	public List<String> getIncentive()
	{
    	Query query = em.createNamedQuery("IncentiveEntity.findAll",IncentiveEntity.class);
    	List<String> incentive_names = query.getResultList();
    	em.flush();
    	return incentive_names;
	}
}
