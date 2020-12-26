package com.zj.study.test.powermock;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Mock final
 * 
 * @author zhaojian
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalEmployeeDao.class)
public class MockTest5 {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		FinalEmployeeDao employeeDao = PowerMockito.mock(FinalEmployeeDao.class);
		Employee employee = new Employee();
		PowerMockito.when(employeeDao.insertEmployee(employee)).thenReturn(true);

		EmployeeService5 employeeService = new EmployeeService5(employeeDao);
		employeeService.createEmployee(employee);
		Mockito.verify(employeeDao).insertEmployee(employee);
	}
}
