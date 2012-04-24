package org.timesheet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.domain.Manager;
import org.timesheet.service.dao.ManagerDao;
import org.timesheet.web.helpers.EntityGenerator;

import java.util.List;

@Controller
@RequestMapping("/managers")
public class ManagerController {

    private ManagerDao managerDao;

    /**
     * Creates new ManagerController
     * @param managerDao Injected DAO for manipulating managers
     */
    @Autowired
    public ManagerController(ManagerDao managerDao) {
        this.managerDao = managerDao;
    }

    /**
     * Retrieves managers, puts them in the model and returns corresponding view
     * @param model Model to put employees to
     * @return managers/list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showManagers(Model model) {
        List<Manager> employees = managerDao.list();
        model.addAttribute("managers", employees);

        return "managers/list";
    }

    /**
     * Deletes manager with specified ID
     * @param id Manager's ID
     * @return redirects to managers
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteManager(@PathVariable("id") long id) {
        Manager toDelete = managerDao.find(id);
        managerDao.remove(toDelete);

        return "redirect:/managers";
    }

    /**
     * Returns manager with specified ID
     * @param id Managers's ID
     * @param model Model to put manager to
     * @return managers/view
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getManager(@PathVariable("id") long id, Model model) {
        Manager manager = managerDao.find(id);
        model.addAttribute("manager", manager);

        return "managers/view";
    }

    /**
     * Updates manager with specified ID
     * @param id Manager's ID
     * @param manager Manager to update (bounded from HTML form)
     * @return redirects to managers
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateManager(@PathVariable("id") long id, Manager manager) {
        manager.setId(id);
        managerDao.update(manager);

        return "redirect:/managers";
    }

    /**
     * Creates form for new manager
     * @param model Model to bind to HTML form
     * @return manager/new
     */
    @RequestMapping(params = "new", method = RequestMethod.GET)
    public String createManagerForm(Model model) {
        model.addAttribute("manager", new Manager());
        return "managers/new";
    }

    /**
     * Saves new manager to the database
     * @param manager Manager to save
     * @return redirects to managers
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addManager(Manager manager) {
        managerDao.add(manager);

        return "redirect:/managers";
    }

}
