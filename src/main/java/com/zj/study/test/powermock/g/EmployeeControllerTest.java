package com.zj.study.test.powermock.g;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 
 * ArgumentMatcher
 * 
 * @author zhaojian
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeController.class)
public class EmployeeControllerTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEmailUseAW() {
		EmployeeService employeeService = PowerMockito.mock(EmployeeService.class);
		PowerMockito.when(employeeService.findEmailByUserName(Mockito.argThat(new ArgumentMatcher<String>() {

			@Override
			public boolean matches(String arg) {
				if (arg.startsWith("wangwenjun") || arg.startsWith("wwj"))
					return true;
				else
					throw new RuntimeException();
			}

		}))).thenReturn("wangwenjun@gmail.com");

		try {
			PowerMockito.whenNew(EmployeeService.class).withAnyArguments().thenReturn(employeeService);
			EmployeeController controller = new EmployeeController();
			String email = controller.getEmail("wangwenjun");
			assertEquals("wangwenjun@gmail.com", email);
			controller.getEmail("liudehua");
			fail("should not process to here.");
		} catch (Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}

	@Test
	public void testGetEmailUseAI() {
		EmployeeService employeeService = PowerMockito.mock(EmployeeService.class);

		PowerMockito.when(employeeService.findEmailByUserName(Mockito.anyString())).then(new Answer<String>() {

			@Override
			public String answer(InvocationOnMock invocation) throws Throwable {
				String argument = (String) invocation.getArguments()[0];
				if ("wangwenjun".equals(argument)) {
					return "wangwenjun@gmail.com";
				} else if ("liudehua".equals(argument)) {
					return "andy@gmail.com";
				}

				throw new NullPointerException();
			}
		});

		try {
			PowerMockito.whenNew(EmployeeService.class).withNoArguments().thenReturn(employeeService);
			EmployeeController controller = new EmployeeController();
			String email = controller.getEmail("wangwenjun");
			assertEquals("wangwenjun@gmail.com", email);
			email = controller.getEmail("liudehua");
			assertEquals("andy@gmail.com", email);
			email = controller.getEmail("JackChen");
			fail("should not process to here.");
		} catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}

	}

}
