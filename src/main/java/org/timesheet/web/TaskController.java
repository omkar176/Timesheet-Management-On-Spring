package org.timesheet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.timesheet.domain.Employee;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.TaskDao;

import java.util.List;

/**
 * Controller for handling Tasks.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private TaskDao taskDao;

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    /**
     * Retrieves tasks, puts them in the model and returns corresponding view
     * @param model Model to put tasks to
     * @return tasks/list
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showTasks(Model model) {
        List<Task> tasks = taskDao.list();
        model.addAttribute("tasks", tasks);

        return "tasks/list";
    }

}
