package com.zj.study.java2s.reflection.classes;
/*
 *     file: ReflexiveInvocation.java
 *  package: oreilly.hcj.reflection
 *
 * This software is granted under the terms of the Common Public License,
 * CPL, which may be found at the following URL:
 * http://www-124.ibm.com/developerworks/oss/CPLv1.0.htm
 *
 * Copyright(c) 2003-2005 by the authors indicated in the @author tags.
 * All Rights are Reserved by the various authors.
 *
 ########## DO NOT EDIT ABOVE THIS LINE ########## */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Demonstration of speed of reflexive versus programatic invocation.
 * 
 * @author <a href=mailto:kraythe@arcor.de>Robert Simmons jr. (kraythe)</a>
 * @version $Revision: 1.3 $
 */
public class ReflexiveInvocation {
	/** Holds value of property value. */
	private String value = "some value";

	/**
	 * Creates a new instance of ReflexiveInvocation
	 */
	public ReflexiveInvocation() {
	}

	/**
	 * Main demo method.
	 * 
	 * @param args
	 *            the command line arguments
	 * 
	 * @throws RuntimeException
	 *             __UNDOCUMENTED__
	 */
	public static void main(final String[] args) {
		try {
			final int CALL_AMOUNT = 1000000;
			final ReflexiveInvocation ri = new ReflexiveInvocation();
			int idx = 0;

			// Call the method without using reflection.
			long millis = System.currentTimeMillis();

			for (idx = 0; idx < CALL_AMOUNT; idx++) {
				ri.getValue();
			}

			System.out.println("Calling method " + CALL_AMOUNT + " times programatically took "
					+ (System.currentTimeMillis() - millis) + " millis");

			// Call while looking up the method in each iteration.
			Method md = null;
			millis = System.currentTimeMillis();

			for (idx = 0; idx < CALL_AMOUNT; idx++) {
				md = ri.getClass().getMethod("getValue", null);
				md.invoke(ri, null);
			}

			System.out.println("Calling method " + CALL_AMOUNT + " times reflexively with lookup took "
					+ (System.currentTimeMillis() - millis) + " millis");

			// Call using a cache of the method.
			md = ri.getClass().getMethod("getValue", null);
			millis = System.currentTimeMillis();

			for (idx = 0; idx < CALL_AMOUNT; idx++) {
				md.invoke(ri, null);
			}

			System.out.println("Calling method " + CALL_AMOUNT + " times reflexively with cache took "
					+ (System.currentTimeMillis() - millis) + " millis");
		} catch (final NoSuchMethodException ex) {
			throw new RuntimeException(ex);
		} catch (final InvocationTargetException ex) {
			throw new RuntimeException(ex);
		} catch (final IllegalAccessException ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Getter for property value.
	 * 
	 * @return Value of property value.
	 */
	public String getValue() {
		return this.value;
	}
}

/* ########## End of File ########## */