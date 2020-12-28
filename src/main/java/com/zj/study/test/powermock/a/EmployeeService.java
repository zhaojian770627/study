package com.zj.study.test.powermock.a;

import com.zj.study.test.powermock.Employee;
import com.zj.study.test.powermock.EmployeeDao;

public class EmployeeService {
	private EmployeeDao employeeDao;

	public EmployeeService(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * 获取所有员工的数量.
	 * 
	 * @return
	 */
	public int getTotalEmployee() {
		return employeeDao.getTotal();
	}

	public void createEmployee(Employee employee) {
		employeeDao.addEmployee(employee);
	}
}
