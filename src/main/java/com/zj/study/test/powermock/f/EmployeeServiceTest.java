package com.zj.study.test.powermock.f;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.zj.study.test.powermock.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);// (1)

		try {
			PowerMockito.whenNew(EmployeeDao.class).withArguments(false, Dialect.MYSQL).thenReturn(employeeDao);// (2)
			EmployeeService employeeService = new EmployeeService();
			Employee employee = new Employee();
			employeeService.createEmployee(employee);
			Mockito.verify(employeeDao).insertEmploye(employee);// (3)
		} catch (Exception e) {
			fail();
		}
	}

}
