package com.zj.study.test.powermock.g;

public class EmployeeController {
	public String getEmail(final String userName) {
		EmployeeService employeeService = new EmployeeService();
		String email = employeeService.findEmailByUserName(userName);
		return email;
	}
}