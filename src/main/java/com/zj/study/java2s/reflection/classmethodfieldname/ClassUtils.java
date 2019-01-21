package com.zj.study.java2s.reflection.classmethodfieldname;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A collection of class management utility methods.
 *
 * @version $Id: ClassUtils.java 587751 2007-10-24 02:41:36Z vgritsenko $
 */
public class ClassUtils {
	public static final String PACKAGE_SEPARATOR = ".";

	/**
	 * Get the short name of the specified class by striping off the package name.
	 * 
	 * @param classname
	 *            Class name.
	 * @return Short class name.
	 */
	public static String stripPackageName(final String classname) {
		int idx = classname.lastIndexOf(PACKAGE_SEPARATOR);

		if (idx != -1)
			return classname.substring(idx + 1, classname.length());
		return classname;
	}

	/**
	 * Create a new instance given a class name
	 *
	 * @param className
	 *            A class name
	 * @return A new instance
	 * @exception Exception
	 *                If an instantiation error occurs
	 */
	public static Object newInstance(String className) throws Exception {
		return loadClass(className).newInstance();
	}

	/**
	 * Load a class given its name. BL: We wan't to use a known
	 * ClassLoader--hopefully the heirarchy is set correctly.
	 *
	 * @param className
	 *            A class name
	 * @return The class pointed to by <code>className</code>
	 * @exception ClassNotFoundException
	 *                If a loading error occurs
	 */
	public static Class loadClass(String className) throws ClassNotFoundException {
		return getClassLoader().loadClass(className);
	}

	/**
	 * Return a resource URL. BL: if this is command line operation, the
	 * classloading issues are more sane. During servlet execution, we explicitly
	 * set the ClassLoader.
	 *
	 * @return The context classloader.
	 * @exception MalformedURLException
	 *                If a loading error occurs
	 */
	public static URL getResource(String resource) throws MalformedURLException {
		return getClassLoader().getResource(resource);
	}

	/**
	 * Return the context classloader. BL: if this is command line operation, the
	 * classloading issues are more sane. During servlet execution, we explicitly
	 * set the ClassLoader.
	 *
	 * @return The context classloader.
	 */
	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}