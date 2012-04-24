package org.timesheet.service.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.timesheet.DomainAwareBase;
import org.timesheet.domain.Manager;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class ManagerDaoTest extends DomainAwareBase {
    
    @Autowired
    private ManagerDao managerDao;
    
    @Test
    public void testAdd() {
        int size = managerDao.list().size();
        managerDao.add(new Manager("test-manager"));

        assertTrue (size < managerDao.list().size());
    }

    @Test
    public void testUpdate() {
        Manager manager = new Manager("test-manager");
        managerDao.add(manager);
        manager.setName("updated");

         managerDao.update(manager);
        Manager found = managerDao.find(manager.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFind() {
        Manager manager = new Manager("test-manager");
        managerDao.add(manager);

        Manager found = managerDao.find(manager.getId());
        assertEquals(found, manager);
    }
    
    @Test
    public void testList() {
        assertEquals(0, managerDao.list().size());
        
        List<Manager> managers = Arrays.asList(
                new Manager("test-1"),
                new Manager("test-2"),
                new Manager("test-3")
        );
        for (Manager manager : managers) {
            managerDao.add(manager);
        }

        List<Manager> found = managerDao.list();
        assertEquals(3, found.size());
        for (Manager manager : found) {
            assertTrue(managers.contains(manager));
        }
    }
    
    @Test
    public void testRemove() {
        Manager manager = new Manager("test-manager");
        managerDao.add(manager);
        
        // successfully added
        assertEquals(manager, managerDao.find(manager.getId()));
        
        // try to remove
        managerDao.remove(manager);
        assertNull(managerDao.find(manager.getId()));
    }
}
