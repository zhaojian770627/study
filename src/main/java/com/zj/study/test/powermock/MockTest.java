package com.zj.study.test.powermock;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

public class MockTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final EmployeeDao employeeDao = new EmployeeDao();
		final EmployeeService service = new EmployeeService(employeeDao);
		int total = service.getTotalEmployee();
		assertEquals(10, total);
	}

	@Test
	public void testWithMock() {
		final EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
		final EmployeeService service = new EmployeeService(employeeDao);
		int total = service.getTotalEmployee();
		assertEquals(10, total);
	}

	@Test
	public void testCreateEmployee() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		Employee employee = new Employee();
		PowerMockito.doNothing().when(employeeDao).addEmployee(employee);
		EmployeeService service = new EmployeeService(employeeDao);
		service.createEmployee(employee);
		Mockito.verify(employeeDao).addEmployee(employee);
	}
}
