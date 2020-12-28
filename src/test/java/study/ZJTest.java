package study;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;

import com.zj.study.test.powermock.EmployeeDao;
import com.zj.study.test.powermock.a.EmployeeService;

public class ZJTest {

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
		final EmployeeDao employeeDao = new EmployeeDao();
		PowerMockito.when(employeeDao.getTotal()).thenReturn(10);
		final EmployeeService service = new EmployeeService(employeeDao);
		int total = service.getTotalEmployee();
		assertEquals(10, total);
	}

}
