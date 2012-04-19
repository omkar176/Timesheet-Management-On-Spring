package org.timesheet.service.impl;

import org.springframework.stereotype.Repository;
import org.timesheet.domain.Manager;
import org.timesheet.service.dao.ManagerDao;

@Repository("managerDao")
public class ManagerDaoImpl extends HibernateDao<Manager, Long> implements ManagerDao {
    // nothing special here
}
