package com.zj.study.java2s.reflection.classes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

public class AppropriateMain {

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
	 * Convert a list of Strings from an Interator into an array of Classes (the
	 * Strings are taken as classnames).
	 * 
	 * @param it
	 *            A java.util.Iterator pointing to a Collection of Strings
	 * @param cl
	 *            The ClassLoader to use
	 * 
	 * @return Array of Classes
	 * 
	 * @throws ClassNotFoundException
	 *             When a class could not be loaded from the specified ClassLoader
	 */
	public final static Class<?>[] convertToJavaClasses(Iterator<String> it, ClassLoader cl)
			throws ClassNotFoundException {
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		while (it.hasNext()) {
			classes.add(convertToJavaClass(it.next(), cl));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Convert a given String into the appropriate Class.
	 * 
	 * @param name
	 *            Name of class
	 * @param cl
	 *            ClassLoader to use
	 * 
	 * @return The class for the given name
	 * 
	 * @throws ClassNotFoundException
	 *             When the class could not be found by the specified ClassLoader
	 */
	private final static Class convertToJavaClass(String name, ClassLoader cl) throws ClassNotFoundException {
		int arraySize = 0;
		while (name.endsWith("[]")) {
			name = name.substring(0, name.length() - 2);
			arraySize++;
		}

		// Check for a primitive type
		Class c = (Class) PRIMITIVE_NAME_TYPE_MAP.get(name);

		if (c == null) {
			// No primitive, try to load it from the given ClassLoader
			try {
				c = cl.loadClass(name);
			} catch (ClassNotFoundException cnfe) {
				throw new ClassNotFoundException("Parameter class not found: " + name);
			}
		}

		// if we have an array get the array class
		if (arraySize > 0) {
			int[] dims = new int[arraySize];
			for (int i = 0; i < arraySize; i++) {
				dims[i] = 1;
			}
			c = Array.newInstance(c, dims).getClass();
		}

		return c;
	}

}