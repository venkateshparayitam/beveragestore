package de.uniba.dsg.dsam.persistence;

import java.util.List;

import de.uniba.dsg.dsam.model.Beverage;

/**
 * 
 * @author GROUP 1 WS2018/19.
 * <p>This is an interface BeverageManagement implemented by BeverageManagementBean.</p>
 */
public interface BeverageManagement {

    public void create(Beverage beverage);
    
    public void update(Beverage beverage);
    
    public void delete(Beverage beverage);
    
    public List<Beverage> showBeverages();
    
    public List<Beverage> display();
}
