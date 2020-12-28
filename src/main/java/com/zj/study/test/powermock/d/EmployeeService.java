package com.zj.study.test.powermock.d;

import com.zj.study.test.powermock.Employee;
import com.zj.study.test.powermock.EmployeeDao;

public class EmployeeService {
	public void saveOrUpdate(Employee employee) {
		final EmployeeDao employeeDao = new EmployeeDao();
		long count = employeeDao.getCount(employee);
		if (count > 0)
			employeeDao.updateEmploye(employee);
		else
			employeeDao.saveEmployee(employee);
	}
}
