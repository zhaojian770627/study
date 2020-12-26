package com.zj.study.test.powermock;

public class EmployeeService2 {
	public EmployeeService2() {
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
}
