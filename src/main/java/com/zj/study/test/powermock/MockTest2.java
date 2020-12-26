package com.zj.study.test.powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService2.class)
public class MockTest2 {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetTotalEmployee() {
		final EmployeeService2 service = new EmployeeService2();
		int total = service.getTotalEmployee();
		assertEquals(10, total);
	}

	@Test
	public void testGetTotalEmployeeWithMock() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		try {
			PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
			PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);

			EmployeeService2 service = new EmployeeService2();
			int total = service.getTotalEmployee();
			assertEquals(10, total);
		} catch (Exception e) {
			fail("测试失败.");
		}
	}

}
