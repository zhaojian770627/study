package com.zj.study.java2s.reflection.classloader;

import java.lang.reflect.Array;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;

/**
 * Equivalently to invoking
 * Thread.currentThread().getContextClassLoader().loadClass(className); but it
 * supports primitive types and array classes of object types or primitive
 * types.
 * 
 * A collection of <code>Class</code> utilities.
 * 
 * @version <tt>$Revision: 2787 $</tt>
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @author <a href="mailto:scott.stark@jboss.org">Scott Stark</a>
 * @author <a href="mailto:dimitris@jboss.org">Dimitris Andreadis<a/>
 */
@SuppressWarnings("unchecked")
public final class Classes {
	/** The string used to separator packages */
	public static final String PACKAGE_SEPARATOR = ".";

	/** The characther used to separator packages */
	public static final char PACKAGE_SEPARATOR_CHAR = '.';

	/** The default package name. */
	public static final String DEFAULT_PACKAGE_NAME = "<default>";

	/**
	 * Describe the class of an object
	 * 
	 * @param object
	 *            the object
	 * @return the description
	 */
	public static String getDescription(Object object) {
		StringBuffer buffer = new StringBuffer();
		describe(buffer, object);
		return buffer.toString();
	}

	/**
	 * Describe the class of an object
	 * 
	 * @param buffer
	 *            the string buffer
	 * @param object
	 *            the object
	 */
	public static void describe(StringBuffer buffer, Object object) {
		if (object == null)
			buffer.append("**null**");
		else
			describe(buffer, object.getClass());
	}

	/**
	 * Describe the class
	 * 
	 * @param buffer
	 *            the string buffer
	 * @param clazz
	 *            the clazz
	 */
	public static void describe(StringBuffer buffer, Class clazz) {
		if (clazz == null)
			buffer.append("**null**");
		else {
			buffer.append("{class=").append(clazz.getName());
			Class[] intfs = clazz.getInterfaces();
			if (intfs.length > 0) {
				buffer.append(" intfs=");
				for (int i = 0; i < intfs.length; ++i) {
					buffer.append(intfs[i].getName());
					if (i < intfs.length - 1)
						buffer.append(", ");
				}
			}
			buffer.append("}");
		}
	}

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
	 * Get the short name of the specified class by striping off the package name.
	 * 
	 * @param type
	 *            Class name.
	 * @return Short class name.
	 */
	public static String stripPackageName(final Class type) {
		return stripPackageName(type.getName());
	}

	/**
	 * Get the package name of the specified class.
	 * 
	 * @param classname
	 *            Class name.
	 * @return Package name or "" if the classname is in the <i>default</i> package.
	 * 
	 * @throws EmptyStringException
	 *             Classname is an empty string.
	 */
	public static String getPackageName(final String classname) {
		if (classname.length() == 0)
			System.out.println("Empty String Exception");

		int index = classname.lastIndexOf(PACKAGE_SEPARATOR);
		if (index != -1)
			return classname.substring(0, index);
		return "";
	}

	/**
	 * Get the package name of the specified class.
	 * 
	 * @param type
	 *            Class.
	 * @return Package name.
	 */
	public static String getPackageName(final Class type) {
		return getPackageName(type.getName());
	}

	// ///////////////////////////////////////////////////////////////////////
	// Primitives //
	// ///////////////////////////////////////////////////////////////////////

	/** Primitive type name -> class map. */
	private static final Map PRIMITIVE_NAME_TYPE_MAP = new HashMap();

	/** Setup the primitives map. */
	static {
		PRIMITIVE_NAME_TYPE_MAP.put("boolean", Boolean.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("byte", Byte.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("char", Character.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("short", Short.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("int", Integer.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("long", Long.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("float", Float.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("double", Double.TYPE);
	}

	/**
	 * Get the primitive type for the given primitive name.
	 * 
	 * <p>
	 * For example, "boolean" returns Boolean.TYPE and so on...
	 * 
	 * @param name
	 *            Primitive type name (boolean, int, byte, ...)
	 * @return Primitive type or null.
	 * 
	 * @exception IllegalArgumentException
	 *                Type is not a primitive class
	 */
	public static Class getPrimitiveTypeForName(final String name) {
		return (Class) PRIMITIVE_NAME_TYPE_MAP.get(name);
	}

	/** Map of primitive types to their wrapper classes */
	private static final Class[] PRIMITIVE_WRAPPER_MAP = { Boolean.TYPE, Boolean.class, Byte.TYPE, Byte.class,
			Character.TYPE, Character.class, Double.TYPE, Double.class, Float.TYPE, Float.class, Integer.TYPE,
			Integer.class, Long.TYPE, Long.class, Short.TYPE, Short.class, };

	/**
	 * Get the wrapper class for the given primitive type.
	 * 
	 * @param type
	 *            Primitive class.
	 * @return Wrapper class for primitive.
	 * 
	 * @exception IllegalArgumentException
	 *                Type is not a primitive class
	 */
	public static Class getPrimitiveWrapper(final Class type) {
		if (!type.isPrimitive()) {
			throw new IllegalArgumentException("type is not a primitive class");
		}

		for (int i = 0; i < PRIMITIVE_WRAPPER_MAP.length; i += 2) {
			if (type.equals(PRIMITIVE_WRAPPER_MAP[i]))
				return PRIMITIVE_WRAPPER_MAP[i + 1];
		}

		// should never get here, if we do then PRIMITIVE_WRAPPER_MAP
		// needs to be updated to include the missing mapping
		System.out.println("Unreachable Statement Exception");
		return null;
	}

	/**
	 * Check if the given class is a primitive wrapper class.
	 * 
	 * @param type
	 *            Class to check.
	 * @return True if the class is a primitive wrapper.
	 */
	public static boolean isPrimitiveWrapper(final Class type) {
		for (int i = 0; i < PRIMITIVE_WRAPPER_MAP.length; i += 2) {
			if (type.equals(PRIMITIVE_WRAPPER_MAP[i + 1])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if the given class is a primitive class or a primitive wrapper class.
	 * 
	 * @param type
	 *            Class to check.
	 * @return True if the class is a primitive or primitive wrapper.
	 */
	public static boolean isPrimitive(final Class type) {
		if (type.isPrimitive() || isPrimitiveWrapper(type)) {
			return true;
		}

		return false;
	}

	/**
	 * Check type against boolean, byte, char, short, int, long, float, double.
	 * 
	 * @param type
	 *            The java type name
	 * @return true if this is a primative type name.
	 */
	public static boolean isPrimitive(final String type) {
		return PRIMITIVE_NAME_TYPE_MAP.containsKey(type);
	}

	/**
	 * Instantiate a java class object
	 * 
	 * @param expected
	 *            the expected class type
	 * @param property
	 *            the system property defining the class
	 * @param defaultClassName
	 *            the default class name
	 * @return the instantiated object
	 */
	public static Object instantiate(Class expected, String property, String defaultClassName) {
		String className = getProperty(property, defaultClassName);
		Class clazz = null;
		try {
			clazz = loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot load class " + className, e);
		}
		Object result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException("Error instantiating " + className, e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error instantiating " + className, e);
		}
		if (expected.isAssignableFrom(clazz) == false)
			throw new RuntimeException("Class " + className + " from classloader " + clazz.getClassLoader()
					+ " is not of the expected class " + expected + " loaded from " + expected.getClassLoader());
		return result;
	}

	// ///////////////////////////////////////////////////////////////////////
	// Class Loading //
	// ///////////////////////////////////////////////////////////////////////

	/**
	 * This method acts equivalently to invoking
	 * <code>Thread.currentThread().getContextClassLoader().loadClass(className);</code>
	 * but it also supports primitive types and array classes of object types or
	 * primitive types.
	 * 
	 * @param className
	 *            the qualified name of the class or the name of primitive type or
	 *            array in the same format as returned by the
	 *            <code>java.lang.Class.getName()</code> method.
	 * @return the Class object for the requested className
	 * 
	 * @throws ClassNotFoundException
	 *             when the <code>classLoader</code> can not find the requested
	 *             class
	 */
	public static Class loadClass(String className) throws ClassNotFoundException {
		return loadClass(className, Thread.currentThread().getContextClassLoader());
	}

	/**
	 * This method acts equivalently to invoking classLoader.loadClass(className)
	 * but it also supports primitive types and array classes of object types or
	 * primitive types.
	 * 
	 * @param className
	 *            the qualified name of the class or the name of primitive type or
	 *            array in the same format as returned by the
	 *            java.lang.Class.getName() method.
	 * @param classLoader
	 *            the ClassLoader used to load classes
	 * @return the Class object for the requested className
	 * 
	 * @throws ClassNotFoundException
	 *             when the <code>classLoader</code> can not find the requested
	 *             class
	 */
	public static Class loadClass(String className, ClassLoader classLoader) throws ClassNotFoundException {
		// ClassLoader.loadClass() does not handle primitive types:
		//
		// B byte
		// C char
		// D double
		// F float
		// I int
		// J long
		// S short
		// Z boolean
		// V void
		//
		if (className.length() == 1) {
			char type = className.charAt(0);
			if (type == 'B')
				return Byte.TYPE;
			if (type == 'C')
				return Character.TYPE;
			if (type == 'D')
				return Double.TYPE;
			if (type == 'F')
				return Float.TYPE;
			if (type == 'I')
				return Integer.TYPE;
			if (type == 'J')
				return Long.TYPE;
			if (type == 'S')
				return Short.TYPE;
			if (type == 'Z')
				return Boolean.TYPE;
			if (type == 'V')
				return Void.TYPE;
			// else throw...
			throw new ClassNotFoundException(className);
		}

		// Check for a primative type
		if (isPrimitive(className) == true)
			return (Class) Classes.PRIMITIVE_NAME_TYPE_MAP.get(className);

		// Check for the internal vm format: Lclassname;
		if (className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';')
			return classLoader.loadClass(className.substring(1, className.length() - 1));

		// first try - be optimistic
		// this will succeed for all non-array classes and array classes that have
		// already been resolved
		//
		try {
			return classLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			// if it was non-array class then throw it
			if (className.charAt(0) != '[')
				throw e;
		}

		// we are now resolving array class for the first time

		// count opening braces
		int arrayDimension = 0;
		while (className.charAt(arrayDimension) == '[')
			arrayDimension++;

		// resolve component type - use recursion so that we can resolve primitive
		// types also
		Class componentType = loadClass(className.substring(arrayDimension), classLoader);

		// construct array class
		return Array.newInstance(componentType, new int[arrayDimension]).getClass();
	}

	/**
	 * Get a system property
	 * 
	 * @param name
	 *            the property name
	 * @param defaultValue
	 *            the default value
	 */
	private static String getProperty(final String name, final String defaultValue) {
		return (String) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				return System.getProperty(name, defaultValue);
			}
		});
	}
}
