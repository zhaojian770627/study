package com.zj.study.java2s.reflection.classloader;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * Used to load classes according to the rules defined in the JDO spec. See
 * section 12.5 of the JDO 1.0.1 spec.
 * <p>
 * This class also provides a {@link #classForName(String,boolean,ClassLoader)
 * static method} for loading classes that utilizes a cache. The cache contains
 * class names that were previously <em>not</em> found, organized by the loader
 * that failed to find them. This speeds up cases where attempts to load the
 * same non-existent class are repeated many times.
 *
 * @author <a href="mailto:jackknifebarber@users.sourceforge.net">Mike
 *         Martin</a>
 * @version $Revision: 1.1 $
 */

public class ClassFinder {

	private static final Map findersByLoader = new WeakHashMap();
	private static final Map nonExistentClassNamesByLoader = new WeakHashMap();

	/**
	 * Returns the class finder instance associated with the context class loader of
	 * the calling thread.
	 */
	public static ClassFinder getInstance() {
		return getInstance(getContextClassLoaderPrivileged());
	}

	/**
	 * Returns the class finder instance associated with the specified class loader.
	 * The specified loader becomes the finder's "original context class loader" for
	 * the algorithm implemented by {@link #classForName(String,boolean,Class)}.
	 *
	 * @param ctxLoader
	 *            the context class loader
	 */
	public static synchronized ClassFinder getInstance(ClassLoader ctxLoader) {
		ClassFinder cf = (ClassFinder) findersByLoader.get(ctxLoader);

		if (cf == null)
			findersByLoader.put(ctxLoader, cf = new ClassFinder(ctxLoader));

		return cf;
	}

	/**
	 * Calls getContextClassLoader() for the current thread in a doPrivileged block.
	 *
	 * @return The context class loader of the current thread.
	 * @exception SecurityException
	 *                If getContextClassLoader() fails.
	 */
	private static ClassLoader getContextClassLoaderPrivileged() throws SecurityException {
		return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				return Thread.currentThread().getContextClassLoader();
			}
		});
	}

	private final ClassLoader origContextClassLoader;

	private ClassFinder(ClassLoader origContextClassLoader) {
		this.origContextClassLoader = origContextClassLoader;
	}

	/**
	 * Returns the <tt>Class</tt> object associated with the class or interface with
	 * the given string name.
	 * <p>
	 * This method implements the algorithm described in section 12.5 of the JDO
	 * 1.0.1 spec. It attempts to load the class using up to three loaders:
	 * <ol>
	 * <li>The loader that loaded the class or instance referred to in the API that
	 * caused this class to be loaded (the context class).</li>
	 * <li>The loader returned in the current context by
	 * <tt>Thread.getContextClassLoader()</tt>.</li>
	 * <li>The loader returned by <tt>Thread.getContextClassLoader()</tt> at the
	 * time this <tt>ClassFinder</tt> was constructed (which should equate to the
	 * time of <tt>PersistenceManagerFactory.getPersistenceManager()</tt>).</li>
	 * </ol>
	 *
	 * @param className
	 *            fully qualified name of the desired class
	 * @param initialize
	 *            whether the class must be initialized
	 * @param contextClass
	 *            another class to serve as context for the loading of the named
	 *            class, or <code>null</code> if there is no such other class
	 *
	 * @return class object representing the desired class
	 * @exception ClassNotFoundException
	 *                if the class cannot be located
	 */
	public Class classForName(String className, boolean initialize, Class contextClass) throws ClassNotFoundException {
		if (contextClass != null) {
			try {
				return classForName(className, initialize, contextClass.getClassLoader());
			} catch (ClassNotFoundException e) {
			}
		}

		return classForName(className, initialize);
	}

	/**
	 * Returns the <tt>Class</tt> object associated with the class or interface with
	 * the given string name.
	 * <p>
	 * This method is equivalent to: <blockquote>
	 * 
	 * <pre>
	 * classForName(className, initialize, null)
	 * </pre>
	 * 
	 * </blockquote>
	 *
	 * @param className
	 *            fully qualified name of the desired class
	 * @param initialize
	 *            whether the class must be initialized
	 *
	 * @return class object representing the desired class
	 * @exception ClassNotFoundException
	 *                if the class cannot be located
	 */
	public Class classForName(String className, boolean initialize) throws ClassNotFoundException {
		try {
			return classForName(className, initialize, getContextClassLoaderPrivileged());
		} catch (ClassNotFoundException e) {
			return classForName(className, initialize, origContextClassLoader);
		}
	}

	/**
	 * Returns the <tt>Class</tt> object associated with the class or interface with
	 * the given string name.
	 * <p>
	 * This method is functionally equivalent to
	 * {@link Class#forName(String,boolean,ClassLoader)} except that it maintains a
	 * cache of class names that are <em>not</em> found. In many cases, especially
	 * in an app server environment, searching for non-existent classes can be a
	 * slow process. It's important because some things in TJDO, notably the macro
	 * mechanism used in e.g. view definitions, will search for class names that
	 * don't exist.
	 *
	 * @param className
	 *            fully qualified name of the desired class
	 * @param initialize
	 *            whether the class must be initialized
	 * @param loader
	 *            class loader from which the class must be loaded
	 *
	 * @return class object representing the desired class
	 * @exception ClassNotFoundException
	 *                if the class cannot be located
	 */
	public static Class classForName(String className, boolean initialize, ClassLoader loader)
			throws ClassNotFoundException {
		Set names;

		synchronized (nonExistentClassNamesByLoader) {
			names = (Set) nonExistentClassNamesByLoader.get(loader);

			if (names == null)
				nonExistentClassNamesByLoader.put(loader, names = new HashSet());
			else {
				if (names.contains(className))
					throw new ClassNotFoundException(className + " (cached from previous lookup attempt)");
			}
		}

		try {
			return Class.forName(className, initialize, loader);
		} catch (ClassNotFoundException e) {

			synchronized (nonExistentClassNamesByLoader) {
				names.add(className);
			}
			throw e;
		}
	}

	/**
	 * Returns a hash code value for this object.
	 */
	public int hashCode() {
		return origContextClassLoader.hashCode();
	}

	/**
	 * Indicates whether some object is "equal to" this one. Two
	 * <tt>ClassFinder</tt> objects are considered equal if their original context
	 * class loaders are all equal.
	 *
	 * @param obj
	 *            the reference object with which to compare
	 *
	 * @return <tt>true</tt> if this object is equal to the obj argument;
	 *         <tt>false</tt> otherwise.
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof ClassFinder))
			return false;

		ClassFinder cf = (ClassFinder) obj;

		return origContextClassLoader.equals(cf.origContextClassLoader);
	}
}
