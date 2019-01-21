package com.zj.study.java2s.reflection.classmethodfieldname;

public class Main {

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