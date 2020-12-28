package com.zj.study.test.powermock.e;

import com.zj.study.test.powermock.Employee;

public class EmployeeService {
	private FinalEmployeeDao employeeDao;

	public EmployeeService(FinalEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void createEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
}
