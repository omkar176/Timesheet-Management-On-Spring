package org.timesheet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.domain.Employee;
import org.timesheet.service.dao.EmployeeDao;
import org.timesheet.web.helpers.EntityGenerator;

import java.util.List;

/**
 * Controller for handling Employees.
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeDao employeeDao;

    /**
     * Creates new EmployeeController
     * @param employeeDao Injected DAO for manipulating employees
     */
    @Autowired
    public EmployeeController(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
        EntityGenerator.crateEmployees(employeeDao);
    }

    /**
     * Retrieves employees, puts them in the model and returns corresponding view
     * @param model Model to put employees to
     * @return employees/list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showEmployees(Model model) {
        List<Employee> employees = employeeDao.list();
        model.addAttribute("employees", employees);

        return "employees/list";
    }

    /**
     * Deletes employee with specified ID
     * @param id Employee's ID
     * @return redirects to employees
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") long id) {
        Employee toDelete = employeeDao.find(id);
        employeeDao.remove(toDelete);

        return "redirect:/employees";
    }

    /**
     * Returns employee with specified ID
     * @param id Employee's ID
     * @param model Model to put employee to
     * @return employees/view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getEmployee(@PathVariable("id") long id, Model model) {
        Employee employee = employeeDao.find(id);
        model.addAttribute("employee", employee);

        return "employees/view";
    }

    /**
     * Updates employee with specified ID
     * @param id Employee's ID
     * @param employee Employee to update (bounded from HTML form)
     * @return redirects to employees
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateEmployee(@PathVariable("id") long id, Employee employee) {
        employee.setId(id);
        employeeDao.update(employee);

        return "redirect:/employees";
    }

    /**
     * Creates form for new employee
     * @param model Model to bind to HTML form
     * @return employees/new
     */
    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employees/new";
    }

    /**
     * Saves new employee to the database
     * @param employee Employee to save
     * @return redirects to employees
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addEmployee(Employee employee) {
        employeeDao.add(employee);

        return "redirect:/employees";
    }
    
}
