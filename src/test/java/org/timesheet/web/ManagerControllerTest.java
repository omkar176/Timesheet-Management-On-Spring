package org.timesheet.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.timesheet.DomainAwareBase;
import org.timesheet.domain.Manager;
import org.timesheet.service.dao.ManagerDao;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"/persistence-beans.xml", "/controllers.xml"})
public class ManagerControllerTest extends DomainAwareBase {
    
    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ManagerController controller;
    
    private Model model; // used for controller
    
    @Before
    public void setUp() {
        model = new ExtendedModelMap();
    }

    @After
    public void cleanUp() {
        List<Manager> managers = managerDao.list();
        for (Manager manager : managers) {
            managerDao.remove(manager);
        }
    }

    @Test
    public void testShowManagers() {
        String view = controller.showManagers(model);
        assertEquals("managers/list", view);

        List<Manager> listFromDao = managerDao.list();
        Collection<?> listFromModel = (Collection<?>) model.asMap().get("managers");

        assertTrue(listFromDao.containsAll(listFromModel));
    }
    
    @Test
    public void testDeleteManager() {
        // prepare ID to delete
        Manager john = new Manager("John Lennon");
        managerDao.add(john);
        long id = john.getId();

        // delete & assert
        String view = controller.deleteManager(id);
        assertEquals("redirect:/managers", view);
        assertNull(managerDao.find(id));
    }
    
    @Test
    public void testGetManager() {
        // prepare manager
        Manager george = new Manager("George Harrison");
        managerDao.add(george);
        long id = george.getId();
        
        // get & assert
        String view = controller.getManager(id, model);
        assertEquals("managers/view", view);
        assertEquals(george, model.asMap().get("manager"));
    }

    @Test
    public void testUpdateManager() {
        // prepare manager
        Manager ringo = new Manager("Ringo Starr");
        managerDao.add(ringo);
        long id = ringo.getId();

        // user alters manager in HTML form
        ringo.setName("Rango Starr");

        // update & assert
        String view = controller.updateManager(id, ringo);
        assertEquals("redirect:/managers", view);
        assertEquals("Rango Starr", managerDao.find(id).getName());
    }

    @Test
    public void testAddManager() {
        // prepare manager
        Manager paul = new Manager("Paul McCartney");
        
        // save but via controller
        String view = controller.addManager(paul);
        assertEquals("redirect:/managers", view);

        // manager is stored in DB
        assertEquals(paul, managerDao.find(paul.getId()));
    }
}
