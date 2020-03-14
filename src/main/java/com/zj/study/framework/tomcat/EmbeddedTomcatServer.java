package com.zj.study.framework.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcatServer {

	public static void main(String[] args) throws LifecycleException {
//		String classpath = System.getProperty("user.dir");
		String classpath = "E://sourcecode//zjtomcat";
		System.out.println(classpath);
		Tomcat tomcat = new Tomcat();
		Connector connector = tomcat.getConnector();
		connector.setPort(9091);
		// 设置Host
		Host host = tomcat.getHost();
		// 我们会根据xml配置文件来
		host.setName("localhost");
		host.setAppBase("webapps");

		Context context = tomcat.addContext(host, "/", classpath);
		if (context instanceof StandardContext) {
			StandardContext standardContext = (StandardContext) context;
			standardContext.setDefaultContextXml("E:/sourcecode/apache-tomcat-8.0.53/conf/web.xml");
			// 我们要把Servlet设置进去
			Wrapper wrapper = tomcat.addServlet("/", "DemoServlet", new DemoServlet());
			wrapper.addMapping("/king");
		}
		// Tomcat跑起来
		tomcat.start();
		// 强制Tomcat server等待，避免main线程执行结束后关闭
		tomcat.getServer().await();
	}

}
