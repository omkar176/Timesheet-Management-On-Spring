package org.timesheet.service.dao;

import org.timesheet.domain.Employee;
import org.timesheet.service.GenericDao;

/**
 * DAO of employee.
 */
public interface EmployeeDao extends GenericDao<Employee, Long> {
    // no additional business operations atm
}
