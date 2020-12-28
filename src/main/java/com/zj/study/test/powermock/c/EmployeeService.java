package com.zj.study.test.powermock.c;

import com.zj.study.test.powermock.Employee;
import com.zj.study.test.powermock.EmployeeUtils;

public class EmployeeService {
	public int getEmployeeCount() {
		return EmployeeUtils.getEmployeeCount();
	}

	public void createEmployee(Employee employee) {
		EmployeeUtils.persistenceEmployee(employee);
	}
}
