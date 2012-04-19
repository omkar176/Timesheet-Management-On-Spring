package org.timesheet.service.impl;

import org.springframework.stereotype.Repository;
import org.timesheet.domain.Employee;
import org.timesheet.service.dao.EmployeeDao;

@Repository("employeeDao")
public class EmployeeDaoImpl extends HibernateDao<Employee, Long> implements EmployeeDao {
    // nothing special here
}
