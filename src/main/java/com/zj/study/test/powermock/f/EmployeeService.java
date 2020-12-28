package com.zj.study.test.powermock.f;

import com.zj.study.test.powermock.Employee;
import com.zj.study.test.powermock.f.EmployeeDao.Dialect;

public class EmployeeService {
	public void createEmployee(final Employee employee) {
		EmployeeDao employeeDao = new EmployeeDao(false, Dialect.MYSQL);
		employeeDao.insertEmploye(employee);
	}
}