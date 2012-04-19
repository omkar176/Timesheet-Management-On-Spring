package org.timesheet.service.impl;

import org.springframework.stereotype.Repository;
import org.timesheet.domain.Task;
import org.timesheet.service.dao.TaskDao;

@Repository("taskDao")
public class TaskDaoImpl extends HibernateDao<Task, Long> implements TaskDao {
    // nothing special here
}
