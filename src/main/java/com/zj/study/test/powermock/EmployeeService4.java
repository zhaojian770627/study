package com.zj.study.test.powermock;

public class EmployeeService4 {
	public void saveOrUpdate(Employee employee) {
		final EmployeeDao employeeDao = new EmployeeDao();
		long count = employeeDao.getCount(employee);
		if (count > 0)
			employeeDao.updateEmploye(employee);
		else
			employeeDao.saveEmployee(employee);
	}
}
