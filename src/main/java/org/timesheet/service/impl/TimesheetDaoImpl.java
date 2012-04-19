package org.timesheet.service.impl;

import org.springframework.stereotype.Repository;
import org.timesheet.domain.Timesheet;
import org.timesheet.service.dao.TimesheetDao;

@Repository("timesheetDao")
public class TimesheetDaoImpl extends HibernateDao<Timesheet, Long> implements TimesheetDao {
    // nothing special here
}
