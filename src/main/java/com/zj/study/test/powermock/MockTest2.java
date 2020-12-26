package com.zj.study.test.powermock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

// 显式的告诉 Junit 使用某个指定的 Runner 来运行 Test Case
// 我们使用了 PowerMockRunner 来运行我们的测试用例，如果不指定的话我们就默认使用的
// 是 Junit 提供的 Runner
@RunWith(PowerMockRunner.class)
// 这个注解是告诉PowerMock为我提前准备一个EmployeeService2的class
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

	@Test
	public void testCreateEmployeeWithMock() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
		try {
			// 这句话就是告诉 PowerMock录制一个这样的构造行为，等我接下来用的时候你就把之前mock的东西给我
			// 而@PrepareForTest是为PowerMock的Runner提前准备一个已经根据某种预期改变过的
			// class，在这个例子中就是EmployeeService，
			PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
			Employee employee = new Employee();
			EmployeeService2 service = new EmployeeService2();
			service.createEmployee(employee);
			Mockito.verify(employeeDao).addEmployee(employee);
		} catch (Exception e) {
			fail();
		}
	}

}
