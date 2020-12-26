package com.zj.study.test.powermock;

public class EmployeeService5 {
	private FinalEmployeeDao employeeDao;

	public EmployeeService5(FinalEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void createEmployee(Employee employee) {
		employeeDao.insertEmployee(employee);
	}
}
