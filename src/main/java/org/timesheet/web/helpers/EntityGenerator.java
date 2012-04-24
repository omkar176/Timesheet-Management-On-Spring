package org.timesheet.web.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Manager;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.service.dao.TaskDao;

/**
 * Small util helper for generating entities to simulate real system.
 */
@Service
public final class EntityGenerator {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TaskDao taskDao;

    public void generateDomain() {
        Employee steve = new Employee("Steve", "Design");
        Employee bill = new Employee("Bill", "Marketing");
        Employee linus = new Employee("Linus", "Programming");

        Manager eric = new Manager("Eric");
        Manager larry = new Manager("Larry");

        employeeDao.add(steve);
        employeeDao.add(bill);
        employeeDao.add(linus);

        managerDao.add(eric);
        managerDao.add(larry);

        Task springTask = new Task("Migration to Spring 3.1", eric, steve, linus);
        Task tomcatTask = new Task("Optimizing Tomcat", eric, bill);
        Task centosTask = new Task("Deploying to CentOS", larry, linus);

        taskDao.add(springTask);
        taskDao.add(tomcatTask);
        taskDao.add(centosTask);
    }
}
