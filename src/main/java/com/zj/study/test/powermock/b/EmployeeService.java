package com.zj.study.test.powermock.b;

import com.zj.study.test.powermock.Employee;
import com.zj.study.test.powermock.EmployeeDao;

public class EmployeeService {
	public EmployeeService() {
	}

	/**
	 * 获取所有员工的数量.
	 * 
	 * @return
	 */
	public int getTotalEmployee() {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.getTotal();
	}

	public void createEmployee(Employee employee) {
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.addEmployee(employee);
	}
}
