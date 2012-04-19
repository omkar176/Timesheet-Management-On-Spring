package org.timesheet.service.dao;

import org.timesheet.domain.Task;
import org.timesheet.service.GenericDao;

/**
 * DAO of Task.
 */
public interface TaskDao extends GenericDao<Task, Long> {
    // no additional business operations atm
}
