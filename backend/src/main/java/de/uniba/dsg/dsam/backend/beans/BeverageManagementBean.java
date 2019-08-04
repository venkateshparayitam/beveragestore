package de.uniba.dsg.dsam.backend.beans;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;

import de.uniba.dsg.dsam.persistence.BeverageManagement;

import javax.ejb.Remote;

/**
 * 
 * @author GROUP 1 WS2018/19
 * BeverageManagementBean class implements the BeverageManagement interface
 * <p>
 * BeverageManagementBean class implements the BeverageManagement interface using the methods to
 * create the beverages, delete the beverage, update beverage quantity.
 * </p>
 */
@Stateless
@Remote(BeverageManagement.class)
public class BeverageManagementBean implements BeverageManagement {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager em;
	
	/**
	 * create method creates a new beverage
	 * <p>
	 * create method creates a new beverage in beverageentity table. it accepts a Beverage object
	 * and persists it in the database.
	 * </p>
	 * @param beverage
	 * @return Nothing.
	 */
    @Override
    public void create(Beverage beverage) {
    	
		BeverageEntity be = new BeverageEntity();
    	be.setManufacturer(beverage.getManufacturer());
    	be.setName(beverage.getName());
    	be.setQuantity(beverage.getQuantity());
    	be.setPrice(beverage.getPrice());
		
    	Incentive i = beverage.getIncentive();
    	
    	if(i instanceof PromotionalGift) {
    		IncentiveEntity ie;
        	PromotionalGift pg = (PromotionalGift)i;
    		PromotionalGiftEntity pge = new PromotionalGiftEntity();
    		pge.setName(pg.getName());
    		pge.setDescription(pg.getDescription());
    		em.persist(pge);
    		ie = pge;
    		be.setIncentiveEntity(ie);
        	em.persist(be);
    	}
    	else if(i instanceof TrialPackage) {
        	IncentiveEntity ie;
        	TrialPackage tp = (TrialPackage)i;
    		TrialPackageEntity tpe = new TrialPackageEntity();
    		tpe.setName(tp.getName());
    		tpe.setDescription(tp.getDescription());
    		em.persist(tpe);
    		ie = tpe;
    		be.setIncentiveEntity(ie);
        	em.persist(be);
    	}
    	
    	
    }
    
    /**
     * update method updates the quantity of beverage
     * <p>
     * update method changes the quantity of the beverage in the beverageentity table whose
     * name matches the name of the Beverage object(accepted as parameter).
     * </p>
     * @param beverage
     * @return Nothing.
     */
    public void update(Beverage beverage) {
    	
    	BeverageEntity be = new BeverageEntity();
    	em.createNamedQuery("updatequantity", BeverageEntity.class)
    		.setParameter("q", beverage.getQuantity())
    		.setParameter("n", beverage.getName())
    		.executeUpdate();
    }
    
    /**
     * delete method deletes the beverage
     * <p>
     * delete method accepts a Beverage object and deletes the beverage from the beverageentity table whose
     * name matches the name of Beverage object.
     * </p>
     * @param beverage
     * @return Nothing.
     */
    public void delete(Beverage beverage) {
    	
    	List<BeverageEntity> lbdiid = em.createNamedQuery("getbeverageincentiveid", BeverageEntity.class)
    							.setParameter("n", beverage.getName())
    							.getResultList();
    	
    	BeverageEntity bdiid = lbdiid.get(0);
    	
    	IncentiveEntity ie = bdiid.getIncentiveEntity();
    	
    	em.createNamedQuery("deletebeverage", BeverageEntity.class)
    		.setParameter("n", beverage.getName())
    		.executeUpdate();
    	
    	em.createNamedQuery("deleteincentive", IncentiveEntity.class)
		.setParameter("n", ie.getId())
		.executeUpdate();
    }
    
    /**
     * showBeverages method retrieves the beverages from database
     * <p>
     * showBeverages method queries for all the beverages from the beverageentity table
     * and returns a list of beverages.
     * </p>
     * @param None.
     * @return List<Beverage>
     */
    public List<Beverage> showBeverages() {
    	
    	List<BeverageEntity> lbe = em.createNamedQuery("getallbeverages").getResultList();
    	List<Beverage> lb = new ArrayList<Beverage>();
    	for(BeverageEntity be : lbe) {
    		Beverage b = new Beverage();
    		b.setManufacturer(be.getManufacturer());
    		b.setName(be.getName());
    		b.setQuantity(be.getQuantity());
    		b.setPrice(be.getPrice());
    		lb.add(b);
    	}
    	return lb;
    }
    
    /**
     * <p>This method returns the list of beverages from the BeverageEntity entity. It calls the 
     * named query BeverageEntity.findAll which is declared in the BeverageEntity class.</p>
     * @param Nothing.
     * @return List<Beverage> Returns list of beverages.
     */
    @Override
    public List<Beverage> display(){
    	List <Beverage> beverages_list = new ArrayList<Beverage>();
    	Beverage beverage; 
    	
    	Query query = em.createNamedQuery("BeverageEntity.findAll",BeverageEntity.class);
    	List<BeverageEntity> list1 = (List<BeverageEntity>)query.getResultList();
    	for( BeverageEntity be:list1 ) {
    		beverage = new Beverage();
            beverage.setManufacturer(be.getManufacturer());
            beverage.setName(be.getName());
            beverage.setPrice(be.getPrice());
            beverage.setQuantity(be.getQuantity());
            
            beverages_list.add(beverage);
    	}
    	em.flush();
    	return beverages_list;
    }
}
