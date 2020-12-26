package com.zj.study.test.powermock;

public class EmployeeService3 {
	public int getEmployeeCount() {
		return EmployeeUtils.getEmployeeCount();
	}

	public void createEmployee(Employee employee) {
		EmployeeUtils.persistenceEmployee(employee);
	}
}
