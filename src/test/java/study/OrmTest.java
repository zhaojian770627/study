package study;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.zj.study.orm.bean.User;
import com.zj.study.orm.xmlparser.FactoryBuilder;
import com.zj.study.orm.xmlparser.FactoryBuilder.Factory;

public class OrmTest {

	@Test
	public void test() throws FileNotFoundException, ParserConfigurationException, SAXException, IOException,
			ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException,
			SecurityException {
		String xmlPath = OrmTest.class.getClassLoader().getResource("com/zj/study/orm/beans.xml").getFile();
		System.out.println(xmlPath);

		FactoryBuilder buider = new FactoryBuilder(xmlPath);
		Factory factory = buider.newFactory();

		User user = (User) factory.getBean("bossID");
		System.out.println(user);
	}

}
