package com.zj.study.test.powermock;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Verifying
 * 
 * 1.测试带分支执行不同的服务
 * 
 * @author zhaojian
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService4.class)
public class MockTest4 {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveOrUpdateCountLessZero() {
		try {
			EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
			PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
			Employee employee = new Employee();

			PowerMockito.when(employeeDao.getCount(employee)).thenReturn(0L);
			EmployeeService4 employeeService = new EmployeeService4();
			employeeService.saveOrUpdate(employee);
			Mockito.verify(employeeDao).saveEmployee(employee);// 验证saveEmployee调用过
			Mockito.verify(employeeDao, Mockito.never()).updateEmploye(employee); // 验证updateEmploye从来没有调用过

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSaveOrUpdateCountMoreThanZero() {
		try {
			EmployeeDao employeeDao = PowerMockito.mock(EmployeeDao.class);
			PowerMockito.whenNew(EmployeeDao.class).withNoArguments().thenReturn(employeeDao);
			Employee employee = new Employee();

			PowerMockito.when(employeeDao.getCount(employee)).thenReturn(1L);
			EmployeeService4 employeeService = new EmployeeService4();
			employeeService.saveOrUpdate(employee);
			Mockito.verify(employeeDao).updateEmploye(employee);// 验证updateEmploye调用过
			Mockito.verify(employeeDao, Mockito.never()).saveEmployee(employee); // 验证saveEmployee从来没有调用过
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
