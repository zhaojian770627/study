package com.zj.study.test.powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 测试静态方法
 * 
 * @author zhaojian
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeUtils.class)
public class MockTest3 {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmployeeCount() {
		final EmployeeService3 employeeService = new EmployeeService3();
		int count = employeeService.getEmployeeCount();
		assertEquals(10, count);
	}

	@Test
	public void testCreateEmployee() {
		final EmployeeService3 employeeService = new EmployeeService3();
		Employee employee = new Employee();
		employeeService.createEmployee(employee);
		assertTrue(true);
	}

	@Test
	public void testGetEmployeeCountWithMock() {
		PowerMockito.mockStatic(EmployeeUtils.class);
		PowerMockito.when(EmployeeUtils.getEmployeeCount()).thenReturn(10);

		final EmployeeService3 employeeService = new EmployeeService3();
		int count = employeeService.getEmployeeCount();
		assertEquals(10, count);
	}

	@Test
	public void testCreateEmployeeWithMock() {
		PowerMockito.mockStatic(EmployeeUtils.class);
		Employee employee = new Employee();
		PowerMockito.doNothing().when(EmployeeUtils.class);

		final EmployeeService3 employeeService = new EmployeeService3();
		employeeService.createEmployee(employee);
//		PowerMockito.verifyStatic(EmployeeUtils.class,times(1));
	}

}
