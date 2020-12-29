package com.zj.study.framework.spring.test;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@ContextConfiguration(classes = MockConfig.class)
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({ConfigUtil.class})
public class SpringTestUsePowerMock {

	@Autowired
    private StudentService studentService;

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MockitoAnnotations.initMocks(this);
		
		AddressService addressService = PowerMockito.mock(AddressService.class);
        ReflectionTestUtils.setField(studentService, "addressService", addressService);
        PowerMockito.when(addressService.getAddress(1L)).thenReturn("mock");

        // 模拟静态方法
        PowerMockito.mockStatic(ConfigUtil.class);
        PowerMockito.when(ConfigUtil.getPath()).thenReturn("staticMock");

        String result = studentService.getAge(1L);
        System.out.println(result);
        
	}

}
