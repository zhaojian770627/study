package com.zj.study.java2s.reflection.classmethodfieldname;

import java.lang.reflect.Method;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class Main {
	public static void main(String[] argv) throws Exception {
		Main m = new Main();
		StringBuffer sb = new StringBuffer();
		displayClassInfo(m.getClass(), sb);
		System.out.println(sb);
	}

	/**
	 * Format a string buffer containing the Class, Interfaces, CodeSource, and
	 * ClassLoader information for the given object clazz.
	 * 
	 * @param clazz
	 *            the Class
	 * @param results
	 *            - the buffer to write the info to
	 */
	public static void displayClassInfo(Class clazz, StringBuffer results) {
		// Print out some codebase info for the clazz
		ClassLoader cl = clazz.getClassLoader();
		results.append("\n");
		results.append(clazz.getName());
		results.append("(");
		results.append(Integer.toHexString(clazz.hashCode()));
		results.append(").ClassLoader=");
		results.append(cl);
		ClassLoader parent = cl;
		while (parent != null) {
			results.append("\n..");
			results.append(parent);
			URL[] urls = getClassLoaderURLs(parent);
			int length = urls != null ? urls.length : 0;
			for (int u = 0; u < length; u++) {
				results.append("\n....");
				results.append(urls[u]);
			}
			if (parent != null)
				parent = parent.getParent();
		}
		CodeSource clazzCS = clazz.getProtectionDomain().getCodeSource();
		if (clazzCS != null) {
			results.append("\n++++CodeSource: ");
			results.append(clazzCS);
		} else
			results.append("\n++++Null CodeSource");

		results.append("\nImplemented Interfaces:");
		Class[] ifaces = clazz.getInterfaces();
		for (int i = 0; i < ifaces.length; i++) {
			Class iface = ifaces[i];
			results.append("\n++");
			results.append(iface);
			results.append("(");
			results.append(Integer.toHexString(iface.hashCode()));
			results.append(")");
			ClassLoader loader = ifaces[i].getClassLoader();
			results.append("\n++++ClassLoader: ");
			results.append(loader);
			ProtectionDomain pd = ifaces[i].getProtectionDomain();
			CodeSource cs = pd.getCodeSource();
			if (cs != null) {
				results.append("\n++++CodeSource: ");
				results.append(cs);
			} else
				results.append("\n++++Null CodeSource");
		}
	}

	/**
	 * Use reflection to access a URL[] getURLs or URL[] getClasspath method so that
	 * non-URLClassLoader class loaders, or class loaders that override getURLs to
	 * return null or empty, can provide the true classpath info.
	 * 
	 * @param cl
	 * @return the urls
	 */
	public static URL[] getClassLoaderURLs(ClassLoader cl) {
		URL[] urls = {};
		try {
			Class returnType = urls.getClass();
			Class[] parameterTypes = {};
			Class clClass = cl.getClass();
			Method getURLs = clClass.getMethod("getURLs", parameterTypes);
			if (returnType.isAssignableFrom(getURLs.getReturnType())) {
				Object[] args = {};
				urls = (URL[]) getURLs.invoke(cl, args);
			}
			if (urls == null || urls.length == 0) {
				Method getCp = clClass.getMethod("getClasspath", parameterTypes);
				if (returnType.isAssignableFrom(getCp.getReturnType())) {
					Object[] args = {};
					urls = (URL[]) getCp.invoke(cl, args);
				}
			}
		} catch (Exception ignore) {
		}
		return urls;
	}

	/**
	 * Returns an instance of the given class name, by calling the default
	 * constructor.
	 *
	 * @param className
	 *            The class name to load and to instantiate.
	 * @param returnNull
	 *            If <code>true</code>, if the class is not found it returns
	 *            <code>true</code>, otherwise it throws a
	 *            <code>TilesException</code>.
	 * @return The new instance of the class name.
	 * @throws CannotInstantiateObjectException
	 *             If something goes wrong during instantiation.
	 * @since 2.0.7
	 */
	public static Object instantiate(String className, boolean returnNull) {
		ClassLoader original = Thread.currentThread().getContextClassLoader();
		if (original == null) {
			Thread.currentThread().setContextClassLoader(Main.class.getClassLoader());
		}
		try {
			Class<?> namedClass = Class.forName(className);
			return namedClass.newInstance();
		} catch (ClassNotFoundException e) {
			if (returnNull) {
				return null;
			}
			throw new RuntimeException("Unable to resolve factory class: '" + className + "'", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Unable to access factory class: '" + className + "'", e);
		} catch (InstantiationException e) {
			throw new RuntimeException("Unable to instantiate factory class: '" + className
					+ "'. Make sure that this class has a default constructor", e);
		} finally {
			// If the original context classloader of the current thread was
			// null, it must be reset.
			if (original == null) {
				Thread.currentThread().setContextClassLoader(null);
			}
		}
	}

}