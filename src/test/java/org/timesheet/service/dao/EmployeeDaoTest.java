package org.timesheet.service.dao;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.timesheet.domain.Employee;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/persistence-beans.xml")
public class EmployeeDaoTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private EmployeeDao employeeDao;

    @After
    public void cleanUp() {
        List<Employee> employees = employeeDao.list();
        for (Employee employee : employees) {
            employeeDao.remove(employee);
        }
    }

    @Test
    public void testAdd() {
        int size = employeeDao.list().size();
        employeeDao.add(new Employee("test-employee", "hackzorz"));

        // list should have one more employee now
        assertTrue (size < employeeDao.list().size());
    }
    
    @Test
    public void testUpdate() {
        Employee employee = new Employee("test-employee", "hackzorz");
        employeeDao.add(employee);
        employee.setName("updated");

        employeeDao.update(employee);
        Employee found = employeeDao.find(employee.getId());
        assertEquals("updated", found.getName());
    }

    @Test
    public void testFind() {
        Employee employee = new Employee("test-employee", "hackzorz");
        employeeDao.add(employee);

        Employee found = employeeDao.find(employee.getId());
        assertEquals(found, employee);
    }

    @Test
    public void testList() {
        assertEquals(0, employeeDao.list().size());
        
        List<Employee> employees = Arrays.asList(
                new Employee("test-1", "testers"),
                new Employee("test-2", "testers"),
                new Employee("test-3", "testers"));
        for (Employee employee : employees) {
            employeeDao.add(employee);
        }

        List<Employee> found = employeeDao.list();
        assertEquals(3, found.size());
        for (Employee employee : found) {
            assertTrue(employees.contains(employee));
        }
    }

    @Test
    public void testRemove() {
        Employee employee = new Employee("test-employee", "hackzorz");
        employeeDao.add(employee);

        // successfully added
        assertEquals(employee, employeeDao.find(employee.getId()));

        // try to remove
        employeeDao.remove(employee);
        assertNull(employeeDao.find(employee.getId()));
    }

}
