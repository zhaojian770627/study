package com.zj.study.test.powermock.h;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/**
 * Spy的使用
 * 
 * @author zhaojian
 *
 */
public class FileServiceTest {

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		FileService fileService = PowerMockito.mock(FileService.class);
//		FileService fileService = new FileService();
		fileService.write("liudehua");
	}

	@Test
	public void testWriteSpy() {
		FileService fileService = PowerMockito.spy(new FileService());
		fileService.write("liudehua");
		File file = new File(System.getProperty("user.dir") + "/wangwenjun.txt");
		assertTrue(file.exists());
	}

}
