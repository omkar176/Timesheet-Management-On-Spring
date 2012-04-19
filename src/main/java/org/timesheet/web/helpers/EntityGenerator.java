package org.timesheet.web.helpers;

import org.timesheet.domain.Employee;
import org.timesheet.service.dao.EmployeeDao;

/**
 * Small util helper for generating entities to simulate real system.
 */
public final class EntityGenerator {
    private EntityGenerator() {} // pure helper
    
    public static void crateEmployees(EmployeeDao dao) {
        dao.add(new Employee("Steve", "Design"));
        dao.add(new Employee("Bill", "Marketing"));
        dao.add(new Employee("Linus", "Programming"));
    }
}
