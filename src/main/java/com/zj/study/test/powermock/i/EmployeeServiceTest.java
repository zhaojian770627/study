package com.zj.study.test.powermock.i;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Mock私有方法
 * 
 * @author zhaojian
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExist() {
		try {
			EmployeeService employeeService = PowerMockito.spy(new EmployeeService());

			PowerMockito.doNothing().when(employeeService, "checkExist", "wangwenjun");
			boolean result = employeeService.exist("wangwenjun");
			assertTrue(result);
			PowerMockito.verifyPrivate(employeeService).invoke("checkExist", "wangwenjun");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
