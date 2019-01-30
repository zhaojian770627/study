package com.zj.study.java2s.reflection.classes;
/*
 * ClassFile.java Chuck McManis
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL purposes and without
 * fee is hereby granted provided that this copyright notice
 * appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY
 * OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. CHUCK MCMANIS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * This class is used to manipulate Java class files in strange and mysterious
 * ways.
 *
 * Usage it typically to feed it an array of bytes that are a class file,
 * manipulate the class, then convert the class back into bytes, and feed the
 * final result to <TT>defineClass()</TT>.
 *
 * @version 1.6, 19 Aug 1995
 * @author Chuck McManis
 * @see AttributeInfo
 * @see ConstantPoolInfo
 * @see MethodInfo
 * @see FieldInfo
 */

public class ClassFile {
	int magic;
	short majorVersion;
	short minorVersion;
	ConstantPoolInfo constantPool[];
	short accessFlags;
	ConstantPoolInfo thisClass;
	ConstantPoolInfo superClass;
	ConstantPoolInfo interfaces[];
	FieldInfo fields[];
	MethodInfo methods[];
	AttributeInfo attributes[];
	boolean isValidClass = false;

	public static final int ACC_PUBLIC = 0x1;
	public static final int ACC_PRIVATE = 0x2;
	public static final int ACC_PROTECTED = 0x4;
	public static final int ACC_STATIC = 0x8;
	public static final int ACC_FINAL = 0x10;
	public static final int ACC_SYNCHRONIZED = 0x20;
	public static final int ACC_THREADSAFE = 0x40;
	public static final int ACC_TRANSIENT = 0x80;
	public static final int ACC_NATIVE = 0x100;
	public static final int ACC_INTERFACE = 0x200;
	public static final int ACC_ABSTRACT = 0x400;

	public boolean debug = false;
	public boolean dumpConstants = false;

	/**
	 * Read a class from InputStream <i>in</i>.
	 */
	public boolean read(InputStream in) throws IOException {
		DataInputStream di = new DataInputStream(in);
		int count;

		magic = di.readInt();
		if (magic != (int) 0xCAFEBABE) {
			return (false);
		}

		majorVersion = di.readShort();
		minorVersion = di.readShort();
		count = di.readShort();
		constantPool = new ConstantPoolInfo[count];
		if (debug)
			System.out.println("read(): Read header...");
		constantPool[0] = new ConstantPoolInfo();
		for (int i = 1; i < constantPool.length; i++) {
			constantPool[i] = new ConstantPoolInfo();
			if (!constantPool[i].read(di)) {
				return (false);
			}
			// These two types take up "two" spots in the table
			if ((constantPool[i].type == ConstantPoolInfo.LONG) || (constantPool[i].type == ConstantPoolInfo.DOUBLE))
				i++;
		}

		/*
		 * Update pointers in the constant table. This turns the table into a real
		 * datastructure.
		 *
		 * TODO: Have it verify that the right arguments are present
		 */
		for (int i = 1; i < constantPool.length; i++) {
			if (constantPool[i] == null)
				continue;
			if (constantPool[i].index1 > 0)
				constantPool[i].arg1 = constantPool[constantPool[i].index1];
			if (constantPool[i].index2 > 0)
				constantPool[i].arg2 = constantPool[constantPool[i].index2];
		}

		if (dumpConstants) {
			for (int i = 1; i < constantPool.length; i++) {
				System.out.println("C" + i + " - " + constantPool[i]);
			}
		}
		accessFlags = di.readShort();

		thisClass = constantPool[di.readShort()];
		superClass = constantPool[di.readShort()];
		if (debug)
			System.out.println("read(): Read class info...");

		/*
		 * Identify all of the interfaces implemented by this class
		 */
		count = di.readShort();
		if (count != 0) {
			if (debug)
				System.out.println("Class implements " + count + " interfaces.");
			interfaces = new ConstantPoolInfo[count];
			for (int i = 0; i < count; i++) {
				int iindex = di.readShort();
				if ((iindex < 1) || (iindex > constantPool.length - 1))
					return (false);
				interfaces[i] = constantPool[iindex];
				if (debug)
					System.out.println("I" + i + ": " + interfaces[i]);
			}
		}
		if (debug)
			System.out.println("read(): Read interface info...");

		/*
		 * Identify all fields in this class.
		 */
		count = di.readShort();
		if (debug)
			System.out.println("This class has " + count + " fields.");
		if (count != 0) {
			fields = new FieldInfo[count];
			for (int i = 0; i < count; i++) {
				fields[i] = new FieldInfo();
				if (!fields[i].read(di, constantPool)) {
					return (false);
				}
				if (debug)
					System.out.println("F" + i + ": " + fields[i].toString(constantPool));
			}
		}
		if (debug)
			System.out.println("read(): Read field info...");

		/*
		 * Identify all the methods in this class.
		 */
		count = di.readShort();
		if (count != 0) {
			methods = new MethodInfo[count];
			for (int i = 0; i < count; i++) {
				methods[i] = new MethodInfo();
				if (!methods[i].read(di, constantPool)) {
					return (false);
				}
				if (debug)
					System.out.println("M" + i + ": " + methods[i].toString());
			}
		}
		if (debug)
			System.out.println("read(): Read method info...");

		/*
		 * Identify all of the attributes in this class
		 */
		count = di.readShort();
		if (count != 0) {
			attributes = new AttributeInfo[count];
			for (int i = 0; i < count; i++) {
				attributes[i] = new AttributeInfo();
				if (!attributes[i].read(di, constantPool)) {
					return (false);
				}
			}
		}
		if (debug) {
			System.out.println("read(): Read attribute info...");
			System.out.println("done.");
		}
		isValidClass = true;
		return (true);
	}

	/**
	 * Write the class out as a stream of bytes to the output stream.
	 *
	 * Generally you will read a class file, manipulate it in some way, and then
	 * write it out again before passing it to <TT>defineClass</TT> in some class
	 * loader.
	 */
	public void write(OutputStream out) throws IOException, Exception {
		DataOutputStream dos = new DataOutputStream(out);

		if (!isValidClass) {
			throw new Exception("ClassFile::write() - Invalid Class");
		}

		dos.writeInt(magic);
		dos.writeShort(majorVersion);
		dos.writeShort(minorVersion);
		dos.writeShort(constantPool.length);
		for (int i = 1; i < constantPool.length; i++) {
			if (constantPool[i] != null)
				constantPool[i].write(dos, constantPool);
		}
		dos.writeShort(accessFlags);
		dos.writeShort(ConstantPoolInfo.indexOf(thisClass, constantPool));
		dos.writeShort(ConstantPoolInfo.indexOf(superClass, constantPool));

		if (interfaces == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(interfaces.length);
			for (int i = 0; i < interfaces.length; i++) {
				dos.writeShort(ConstantPoolInfo.indexOf(interfaces[i], constantPool));
			}
		}

		if (fields == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(fields.length);
			for (int i = 0; i < fields.length; i++) {
				fields[i].write(dos, constantPool);
			}
		}

		if (methods == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(methods.length);
			for (int i = 0; i < methods.length; i++) {
				methods[i].write(dos, constantPool);
			}
		}

		if (attributes == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(attributes.length);
			for (int i = 0; i < attributes.length; i++) {
				attributes[i].write(dos, constantPool);
			}
		}
	}

	/**
	 * Returns a string that represents what the access flags are set for. So 0x14
	 * returns "public final "
	 */
	public static String accessString(short flags) {
		StringBuffer x = new StringBuffer();

		if ((flags & ACC_PUBLIC) != 0) {
			x.append("public ");
		}

		if ((flags & ACC_PRIVATE) != 0) {
			x.append("private ");
		}

		if ((flags & ACC_PROTECTED) != 0) {
			x.append("protected ");
		}

		if ((flags & ACC_STATIC) != 0) {
			x.append("static ");
		}

		if ((flags & ACC_FINAL) != 0) {
			x.append("final ");
		}

		if ((flags & ACC_SYNCHRONIZED) != 0) {
			x.append("synchronized ");
		}

		if ((flags & ACC_THREADSAFE) != 0) {
			x.append("threadsafe ");
		}

		if ((flags & ACC_TRANSIENT) != 0) {
			x.append("transient ");
		}

		if ((flags & ACC_NATIVE) != 0) {
			x.append("native ");
		}

		if ((flags & ACC_INTERFACE) != 0) {
			x.append("interface ");
		}

		if ((flags & ACC_ABSTRACT) != 0) {
			x.append("abstract ");
		}

		return (x.toString());
	}

	/**
	 * Takes a type signature and a string representing a variable name and returns
	 * a declaration for that variable name.
	 *
	 * For example, passing this the strings "[B" and "myArray" will return the
	 * string "byte myArray[]"
	 */
	public static String typeString(String typeString, String varName) {
		int isArray = 0;
		int ndx = 0;
		StringBuffer x = new StringBuffer();

		while (typeString.charAt(ndx) == '[') {
			isArray++;
			ndx++;
		}

		switch (typeString.charAt(ndx)) {
		case 'B':
			x.append("byte ");
			break;
		case 'C':
			x.append("char ");
			break;
		case 'D':
			x.append("double ");
			break;
		case 'F':
			x.append("float ");
			break;
		case 'I':
			x.append("int ");
			break;
		case 'J':
			x.append("long ");
			break;
		case 'L':
			for (int i = ndx + 1; i < typeString.indexOf(';'); i++) {
				if (typeString.charAt(i) != '/')
					x.append(typeString.charAt(i));
				else
					x.append('.');
			}
			x.append(" ");
			break;
		case 'V':
			x.append("void ");
			break;
		case 'S':
			x.append("short ");
			break;
		case 'Z':
			x.append("boolean ");
			break;
		}
		x.append(varName);
		while (isArray > 0) {
			x.append("[]");
			isArray--;
		}
		return (x.toString());
	}

	/**
	 * Returns the next signature from a string of concatenated signatures. For
	 * example if the signature was "[BII", this method would return "II"
	 */
	public static String nextSig(String sig) {
		int ndx = 0;
		String x;

		while (sig.charAt(ndx) == '[')
			ndx++;

		if (sig.charAt(ndx) == 'L') {
			while (sig.charAt(ndx) != ';')
				ndx++;
		}
		ndx++;
		x = (sig.substring(ndx));
		return (x);
	}

	/**
	 * Print the name of a class in "canonical form"
	 */
	private String printClassName(String s) {
		StringBuffer x;

		if (s.charAt(0) == '[') {
			return (typeString(s, ""));
		}

		x = new StringBuffer();
		for (int j = 0; j < s.length(); j++) {
			if (s.charAt(j) == '/')
				x.append('.');
			else
				x.append(s.charAt(j));
		}
		return (x.toString());

	}

	public String getClassName() {
		return printClassName(thisClass.arg1.strValue);
	}

	/**
	 * The boring version of display().
	 */
	public String toString() {
		return ("Class File (Version " + majorVersion + "." + minorVersion + ") for class " + thisClass.arg1);
	}

	/**
	 * Write out a text version of this class.
	 */
	public void display(PrintStream ps) throws Exception {
		int i;
		String myClassName;
		String mySuperClassName;
		String packageName = null;

		if (!isValidClass) {
			ps.println("Not a valid class");
		}

		myClassName = printClassName(thisClass.arg1.strValue);
		mySuperClassName = printClassName(superClass.arg1.strValue);
		if (myClassName.indexOf('.') > 0) {
			packageName = myClassName.substring(0, myClassName.lastIndexOf('.'));
			myClassName = myClassName.substring(myClassName.lastIndexOf('.') + 1);
			ps.println("package " + packageName + "\n");
		}

		for (i = 1; i < constantPool.length; i++) {
			if (constantPool[i] == null)
				continue;
			if ((constantPool[i] == thisClass) || (constantPool[i] == superClass))
				continue;
			if (constantPool[i].type == ConstantPoolInfo.CLASS) {
				String s = constantPool[i].arg1.strValue;
				if (s.charAt(0) == '[')
					continue;
				s = printClassName(constantPool[i].arg1.strValue);
				if ((packageName != null) && (s.startsWith(packageName)))
					continue;
				ps.println("import " + printClassName(s) + ";");
			}
		}
		ps.println();
		ps.println("/*");
		DataInputStream dis;
		ConstantPoolInfo cpi;

		if (attributes != null) {
			ps.println(" * This class has " + attributes.length + " optional class attributes.");
			ps.println(" * These attributes are: ");
			for (i = 0; i < attributes.length; i++) {
				String attrName = attributes[i].name.strValue;
				dis = new DataInputStream(new ByteArrayInputStream(attributes[i].data));

				ps.println(" * Attribute " + (i + 1) + " is of type " + attributes[i].name);
				if (attrName.compareTo("SourceFile") == 0) {
					cpi = null;
					try {
						cpi = constantPool[dis.readShort()];
					} catch (IOException e) {
					}
					ps.println(" *  SourceFile : " + cpi);
				} else {
					ps.println(" *  TYPE (" + attrName + ")");
				}
			}
		} else {
			ps.println(" * This class has NO optional class attributes.");
		}
		ps.println(" */\n");
		ps.print(accessString(accessFlags) + "class " + myClassName + " extends " + mySuperClassName);
		if (interfaces != null) {
			ps.print(" implements ");
			for (i = 0; i < interfaces.length - 1; i++) {
				ps.print(interfaces[i].arg1.strValue + ", ");
			}
			ps.print(interfaces[interfaces.length - 1].arg1.strValue);
		}
		ps.println(" {\n");
		if (fields != null) {
			ps.println("/* Instance Variables */");
			for (i = 0; i < fields.length; i++) {
				ps.println("    " + fields[i].toString(constantPool) + ";");
			}
		}

		if (methods != null) {
			ps.println("\n/* Methods */");
			for (i = 0; i < methods.length; i++) {
				ps.println("    " + methods[i].toString(myClassName));
			}
		}
		ps.println("\n}");
	}

	public ConstantPoolInfo getConstantRef(short index) {
		return (constantPool[index]);
	}

	/**
	 * Add a single constant pool item and return its index. If the item is already
	 * in the pool then the index of the <i>preexisting</i> item is returned. Thus
	 * you cannot assume that a pointer to your item will be useful.
	 */
	public short addConstantPoolItem(ConstantPoolInfo item) throws Exception {
		ConstantPoolInfo newConstantPool[];
		ConstantPoolInfo cp;

		cp = item.inPool(constantPool);
		if (cp != null)
			return ConstantPoolInfo.indexOf(cp, constantPool);

		newConstantPool = new ConstantPoolInfo[constantPool.length + 1];
		for (int i = 1; i < constantPool.length; i++) {
			newConstantPool[i] = constantPool[i];
		}
		newConstantPool[constantPool.length] = item;
		constantPool = newConstantPool;
		return ConstantPoolInfo.indexOf(item, constantPool);
	}

	/**
	 * Add some items to the constant pool. This is used to add new items to the
	 * constant pool. The items references in arg1 and arg2 are expected to be valid
	 * pointers (if necessary). Pruning is done to prevent adding redundant items to
	 * the list and to preserve string space.
	 *
	 * The algorithm is simple, first identify pool items containing constants in
	 * the list of items to be added that are already in the constant pool. If any
	 * are found to already exist, change the pointers in the non-constant items to
	 * point to the ones in the pool rather than the ones in the list. Next check to
	 * see if any of the non-constant items are already in the pool and if so fix up
	 * the others in the list to point to the ones in the pool. Finally, add any
	 * items (there must be at least one) from the item list that aren't already in
	 * the pool, all of the pointers will already be fixed.
	 *
	 * NOTE: Since constants in the constant pool may be referenced <i>inside</i>
	 * the opaque portion of attributes the constant table cannot be re-ordered,
	 * only extended.
	 */
	public void addConstantPoolItems(ConstantPoolInfo items[]) {
		ConstantPoolInfo newArg;
		ConstantPoolInfo newConstantPool[];
		boolean delete[] = new boolean[items.length];

		/* Step one, look for matching constants */
		for (int j = 0; j < items.length; j++) {
			if ((items[j].type == ConstantPoolInfo.ASCIZ) || (items[j].type == ConstantPoolInfo.UNICODE)
					|| (items[j].type == ConstantPoolInfo.INTEGER) || (items[j].type == ConstantPoolInfo.LONG)
					|| (items[j].type == ConstantPoolInfo.FLOAT) || (items[j].type == ConstantPoolInfo.DOUBLE)) {

				// Look for this item in the constant pool
				delete[j] = false;
				newArg = items[j].inPool(constantPool);
				if (newArg != null) {
					// replace the references in our list.
					delete[j] = true; // mark it for deletion
					for (int i = 0; i < items.length; i++) {
						if (items[i].arg1 == items[j])
							items[i].arg1 = newArg;
						if (items[i].arg2 == items[j])
							items[i].arg2 = newArg;
					}
				}
			}
		}

		/* Step two : now match everything else */
		for (int j = 0; j < items.length; j++) {
			if ((items[j].type == ConstantPoolInfo.CLASS) || (items[j].type == ConstantPoolInfo.FIELDREF)
					|| (items[j].type == ConstantPoolInfo.METHODREF) || (items[j].type == ConstantPoolInfo.STRING)
					|| (items[j].type == ConstantPoolInfo.INTERFACE)
					|| (items[j].type == ConstantPoolInfo.NAMEANDTYPE)) {

				// Look for this item in the constant pool
				delete[j] = false;
				newArg = items[j].inPool(constantPool);
				if (newArg != null) {
					// replace the references in our list.
					delete[j] = true; // mark it for deletion
					for (int i = 0; i < items.length; i++) {
						if (items[i].arg1 == items[j])
							items[i].arg1 = newArg;
						if (items[i].arg2 == items[j])
							items[i].arg2 = newArg;
					}
				}
			}
		}

		/* Step three: Add the surviving items to the pool */
		int count = 0;
		for (int i = 0; i < items.length; i++) {
			if (!delete[i])
				count++;
		}
		// count == # of survivors
		newConstantPool = new ConstantPoolInfo[constantPool.length + count];
		for (int i = 1; i < constantPool.length; i++) {
			newConstantPool[i] = constantPool[i];
		}
		// newConstantPool == existing constantPool

		int ndx = 0;
		for (int i = constantPool.length; i < newConstantPool.length; i++) {
			while (delete[ndx])
				ndx++;
			newConstantPool[i] = items[ndx];
			ndx++;
		}
		// newConstantPool == existing + new
		constantPool = newConstantPool;
		// all done.
	}

	/**
	 * Add a new optional class Attribute.
	 *
	 * Items is an array of constant pool items that are first added to the constant
	 * pool. At a minimum items[0] must be an ASCIZ item with the name of the
	 * attribute. If the body of the attribute references constant pool items these
	 * should be in the item list as well.
	 */
	public void addAttribute(AttributeInfo newAttribute) {

		if (attributes == null) {
			attributes = new AttributeInfo[1];
			attributes[0] = newAttribute;
		} else {
			AttributeInfo newAttrList[] = new AttributeInfo[1 + attributes.length];
			for (int i = 0; i < attributes.length; i++) {
				newAttrList[i] = attributes[i];
			}
			newAttrList[attributes.length] = newAttribute;
			attributes = newAttrList;
		}
	}

	/**
	 * Return the attribute named 'name' from the class file.
	 */
	public AttributeInfo getAttribute(String name) {
		if (attributes == null)
			return null;
		for (int i = 0; i < attributes.length; i++) {
			if (name.compareTo(attributes[i].name.toString()) == 0)
				return attributes[i];
		}
		return (null);
	}

	/**
	 * Return a constant pool item from this class. (note does fixup of indexes to
	 * facilitate extracting nested or linked items.
	 */
	public ConstantPoolInfo getConstantPoolItem(short index) throws Exception {
		ConstantPoolInfo cp;

		if ((index <= 0) || (index > (constantPool.length - 1)))
			return (null);
		cp = constantPool[index];
		if (cp.arg1 != null)
			cp.index1 = ConstantPoolInfo.indexOf(cp.arg1, constantPool);
		if (cp.arg2 != null)
			cp.index2 = ConstantPoolInfo.indexOf(cp.arg2, constantPool);
		return cp;
	}

	/*
	 * Examples of mysterious things you can do to a class file before writing it
	 * back out. These methods are not currently functional. (that would be too easy
	 * :-)
	 */

	/**
	 * Map occurences of class <i>oldClass</i> to occurrences of class
	 * <i>newClass</i>. This method is used to retarget accesses to one class,
	 * seamlessly to another.
	 *
	 * The format for the class name is slash (/) separated so the class
	 * <tt>util.ClassFile</tt> would be represented as <tt>util/ClassFile</tt>
	 */
	public void mapClass(String oldClass, String newClass) {
		if (debug)
			System.out.println("Mapping class name " + oldClass + " ==> " + newClass + " for class " + thisClass.arg1);
		for (int i = 0; i < constantPool.length; i++) {
			if (constantPool[i].type == ConstantPoolInfo.CLASS) {
				String cname = constantPool[i].arg1.strValue;
				if (cname.compareTo(oldClass) == 0) {
					if (debug) {
						System.out.println("REPLACING " + cname + " with " + newClass);
					}
					constantPool[i].arg1.strValue = newClass;
				}
			}
		}
	}

	/**
	 * Map occurences of package <i>oldPackage</i> to package <i>newPackage</i>.
	 *
	 * The format for the package name is slash (/) separated so the package
	 * <tt>java.util</tt> would be represented as <tt>java/util</tt>
	 */
	public void mapPackage(String oldPackage, String newPackage) {
		for (int i = 0; i < constantPool.length; i++) {
			if (constantPool[i].type == ConstantPoolInfo.CLASS) {
				String cname = constantPool[i].arg1.strValue;
				if (cname.startsWith(oldPackage)) {
					constantPool[i].arg1.strValue = newPackage + cname.substring(cname.lastIndexOf('/'));
				}
			}
		}
	}

	/**
	 * Delete a named method from this class. This method is used to excise specific
	 * methods from the loaded class. The actual method code remains, however the
	 * method signature is deleted from the constant pool. If this method is called
	 * by a class the exception IncompatibleClassChangeException is generated by the
	 * runtime.
	 */
	public void deleteMethod(String name, String signature) {
		for (int i = 0; i < constantPool.length; i++) {
			if (constantPool[i].type == ConstantPoolInfo.CLASS) {
			}
		}
	}
}

/*
 * @(#)ConstantPoolInfo.java 1.5 95/08/16 Chuck McManis
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. CHUCK MCMANIS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 * ITS DERIVATIVES.
 */

/**
 * This class defines an entry in the constant pool for a Java class. The class
 * file is primarily composed of ConstantPool entries and manipulation is done
 * by modifying those entries.
 *
 * @version 1.5, 16 Aug 1995
 * @author Chuck McManis
 * @see ClassFile
 */

class ConstantPoolInfo {
	int type; // type of this item
	String name; // String for the type
	ConstantPoolInfo arg1; // index to first argument
	ConstantPoolInfo arg2; // index to second argument
	short index1, index2;
	String strValue; // ASCIZ String value
	int intValue;
	long longValue;
	float floatValue;
	double doubleValue;

	public static final int CLASS = 7;
	public static final int FIELDREF = 9;
	public static final int METHODREF = 10;
	public static final int STRING = 8;
	public static final int INTEGER = 3;
	public static final int FLOAT = 4;
	public static final int LONG = 5;
	public static final int DOUBLE = 6;
	public static final int INTERFACE = 11;
	public static final int NAMEANDTYPE = 12;
	public static final int ASCIZ = 1;
	public static final int UNICODE = 2;

	/**
	 * Construct a new ConstantPoolInfo object that is of type ASCIZ
	 */
	public ConstantPoolInfo(String value) {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = ASCIZ;
		strValue = value;
	}

	/**
	 * Construct a new ConstantPoolInfo object that is of type INTEGER
	 */
	public ConstantPoolInfo(int value) {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = INTEGER;
		intValue = value;
	}

	/**
	 * Construct a new ConstantPoolInfo object that is of type FLOAT
	 */
	public ConstantPoolInfo(float value) {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = FLOAT;
		floatValue = value;
	}

	/**
	 * Construct a new ConstantPoolInfo object that is of type LONG
	 */
	public ConstantPoolInfo(long value) {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = LONG;
		longValue = value;
	}

	/**
	 * Construct a new ConstantPoolInfo object that is of type DOUBLE
	 */
	public ConstantPoolInfo(double value) {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = DOUBLE;
		doubleValue = value;
	}

	/**
	 * Generic constructor
	 */
	public ConstantPoolInfo() {
		index1 = -1;
		index2 = -1;
		arg1 = null;
		arg2 = null;
		type = -1;
	}

	/**
	 * return the type of this constant pool item.
	 */
	public int isType() {
		return (type);
	}

	public boolean read(DataInputStream dis) throws IOException {
		int len;
		char c;

		type = dis.readByte();
		switch (type) {
		case CLASS:
			name = "Class";
			index1 = dis.readShort();
			index2 = -1;
			break;
		case FIELDREF:
			name = "Field Reference";
			index1 = dis.readShort();
			index2 = dis.readShort();
			break;
		case METHODREF:
			name = "Method Reference";
			index1 = dis.readShort();
			index2 = dis.readShort();
			break;
		case INTERFACE:
			name = "Interface Method Reference";
			index1 = dis.readShort();
			index2 = dis.readShort();
			break;
		case NAMEANDTYPE:
			name = "Name and Type";
			index1 = dis.readShort();
			index2 = dis.readShort();
			break;
		case STRING:
			name = "String";
			index1 = dis.readShort();
			index2 = -1;
			break;
		case INTEGER:
			name = "Integer";
			intValue = dis.readInt();
			break;
		case FLOAT:
			name = "Float";
			floatValue = dis.readFloat();
			break;
		case LONG:
			name = "Long";
			longValue = dis.readLong();
			break;
		case DOUBLE:
			name = "Double";
			doubleValue = dis.readDouble();
			break;
		case ASCIZ:
		case UNICODE:
			if (type == ASCIZ)
				name = "ASCIZ";
			else
				name = "UNICODE";

			StringBuffer xxBuf = new StringBuffer();

			len = dis.readShort();
			while (len > 0) {
				c = (char) (dis.readByte());
				xxBuf.append(c);
				len--;
			}
			strValue = xxBuf.toString();
			break;
		default:
			System.out.println("Warning bad type.");
		}
		return (true);
	}

	public void write(DataOutputStream dos, ConstantPoolInfo pool[]) throws IOException, Exception {
		dos.write(type);
		switch (type) {
		case CLASS:
		case STRING:
			dos.writeShort(indexOf(arg1, pool));
			break;
		case FIELDREF:
		case METHODREF:
		case INTERFACE:
		case NAMEANDTYPE:
			dos.writeShort(indexOf(arg1, pool));
			dos.writeShort(indexOf(arg2, pool));
			break;
		case INTEGER:
			dos.writeInt(intValue);
			break;
		case FLOAT:
			dos.writeFloat(floatValue);
			break;
		case LONG:
			dos.writeLong(longValue);
			break;
		case DOUBLE:
			dos.writeDouble(doubleValue);
			break;
		case ASCIZ:
		case UNICODE:
			dos.writeShort(strValue.length());
			dos.writeBytes(strValue);
			break;
		default:
			throw new Exception("ConstantPoolInfo::write() - bad type.");
		}
	}

	public String toString() {
		StringBuffer s;

		if (type == ASCIZ) {
			return (strValue);
		}

		if (type == INTEGER) {
			return ("= " + intValue);
		}

		if (type == LONG) {
			return ("= " + longValue);
		}

		if (type == FLOAT) {
			return ("= " + floatValue);
		}

		if (type == DOUBLE) {
			return ("= " + doubleValue);
		}

		s = new StringBuffer();
		s.append(name);
		s.append(":");
		if (arg1 != null)
			s.append(arg1.toString());
		else if (index1 != -1)
			s.append("I1[" + index1 + "], ");
		if (arg2 != null)
			s.append(arg2.toString());
		else if (index2 != -1)
			s.append("I2[" + index2 + "], ");
		return (s.toString());
	}

	public static short indexOf(ConstantPoolInfo item, ConstantPoolInfo pool[]) throws Exception {
		for (int i = 0; i < pool.length; i++) {
			if (item == pool[i])
				return (short) i;
		}
		throw new Exception("ConstantPoolInfo:: indexOf() - item not in pool.");
	}

	/**
	 * Returns true if these constant pool items are identical.
	 */
	public boolean isEqual(ConstantPoolInfo cp) {
		if (cp == null)
			return false;

		if (cp.type != type)
			return (false);
		switch (cp.type) {
		case CLASS:
		case STRING:
			return (arg1 == cp.arg1);
		case FIELDREF:
		case METHODREF:
		case INTERFACE:
		case NAMEANDTYPE:
			return ((arg1 == cp.arg1) && (arg2 == cp.arg2));
		case INTEGER:
			return (cp.intValue == intValue);
		case FLOAT:
			return (cp.floatValue == floatValue);
		case LONG:
			return (cp.longValue == longValue);
		case DOUBLE:
			return (cp.doubleValue == doubleValue);
		case ASCIZ:
		case UNICODE:
			return (cp.strValue.compareTo(strValue) == 0);
		}
		return (false);
	}

	/**
	 * Returns the reference to the constant pool item that is already in pool, that
	 * matches this one.
	 */
	public ConstantPoolInfo inPool(ConstantPoolInfo pool[]) {
		for (int i = 1; i < pool.length; i++) {
			if (isEqual(pool[i]))
				return (pool[i]);
		}
		return null;
	}

}

/*
 * @(#)MethodInfo.java 1.4 95/08/16 Chuck McManis
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. CHUCK MCMANIS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 * ITS DERIVATIVES.
 */

/**
 * This class describes a Method as it is stored in the class file. The
 * attribute associated with method is the code that actually implements the
 * method. Since we don't need to manipulate the byte codes directly we leave
 * them as an opaque chunk in the attributes[] array. References in the code are
 * all references into the constant table so when we are modifing a class to use
 * a different object we needn't get into the code level.
 *
 * @version 1.4, 16 Aug 1995
 * @author Chuck McManis
 * @see ClassFile
 */

class MethodInfo {
	short accessFlags;
	ConstantPoolInfo name;
	ConstantPoolInfo signature;
	AttributeInfo attributes[];

	/**
	 * Read a method_info from the data stream.
	 */
	public boolean read(DataInputStream di, ConstantPoolInfo pool[]) throws IOException {
		int count;

		accessFlags = di.readShort();
		name = pool[di.readShort()];
		signature = pool[di.readShort()];
		count = di.readShort();
		if (count != 0) {
			attributes = new AttributeInfo[count];
			for (int i = 0; i < count; i++) {
				attributes[i] = new AttributeInfo(); // "code"
				if (!attributes[i].read(di, pool)) {
					return (false);
				}
			}
		}
		return (true);
	}

	/**
	 * Write out a method_info, do constant table fixups on the write.
	 */
	public void write(DataOutputStream dos, ConstantPoolInfo pool[]) throws IOException, Exception {
		dos.writeShort(accessFlags);
		dos.writeShort(ConstantPoolInfo.indexOf(name, pool));
		dos.writeShort(ConstantPoolInfo.indexOf(signature, pool));
		if (attributes == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(attributes.length);
			for (int i = 0; i < attributes.length; i++)
				attributes[i].write(dos, pool);
		}
	}

	/**
	 * print out the method, much as you would see it in the source file. The string
	 * ClassName is substituted for &LTinit&GT when printing.
	 */
	public String toString(String className) {
		StringBuffer x = new StringBuffer();
		boolean isArray = false;
		String paramSig;
		String returnSig;
		int ndx = 0;
		StringBuffer parameterList = new StringBuffer();
		char initialParameter = 'a';
		StringBuffer varName = new StringBuffer();

		String s = signature.strValue;
		paramSig = s.substring(s.indexOf('(') + 1, s.indexOf(')'));
		returnSig = s.substring(s.indexOf(')') + 1);

		x.append(ClassFile.accessString(accessFlags));
		/* catch constructors */
		if ((className != null) && (name.toString().startsWith("<init>")))
			parameterList.append(className);
		else
			parameterList.append(name.toString());
		parameterList.append("(");
		if ((paramSig.length() > 0) && paramSig.charAt(0) != 'V') {
			while (paramSig.length() > 0) {
				varName.setLength(0);
				varName.append(initialParameter);
				initialParameter++;
				parameterList.append(ClassFile.typeString(paramSig, varName.toString()));
				paramSig = ClassFile.nextSig(paramSig);
				if (paramSig.length() > 0)
					parameterList.append(", ");
			}

		}
		parameterList.append(")");
		x.append(ClassFile.typeString(returnSig, parameterList.toString()));
		x.append(";");
		return (x.toString());
	}

	/**
	 * Generic toString method, init method is unchanged.
	 */
	public String toString() {
		return (toString((String) null));
	}
}
/*
 * @(#)AttributeInfo.java 1.4 95/08/16 Chuck McManis
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. CHUCK MCMANIS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 * ITS DERIVATIVES.
 */

/**
 * This class defines the generic Attribute type for Java class files. It is a
 * little bit smart in that for some Attributes it can display them
 * intelligently if it also has access to the constant pool of the current
 * class.
 *
 * @version 1.4, 16 Aug 1995
 * @author Chuck McManis
 * @see ClassFile
 */
class AttributeInfo {
	ConstantPoolInfo name; // attribute name
	byte data[]; // attribute's contents

	public AttributeInfo(ConstantPoolInfo newName, byte newData[]) {
		name = name;
		data = newData;
	}

	public AttributeInfo() {
	}

	public boolean read(DataInputStream di, ConstantPoolInfo pool[]) throws IOException {
		int len;

		name = pool[di.readShort()];
		len = di.readInt();
		data = new byte[len];
		len = di.read(data);
		if (len != data.length)
			return (false);
		return (true);
	}

	public void write(DataOutputStream dos, ConstantPoolInfo pool[]) throws IOException, Exception {
		dos.writeShort(ConstantPoolInfo.indexOf(name, pool));
		dos.writeInt(data.length);
		dos.write(data, 0, data.length);
	}

	short indexFromBytes(byte a[]) {
		return (short) (((a[0] << 8) & (0xff << 8)) | ((a[1] << 0) & (0xff << 0)));
	}

	public String toString(ConstantPoolInfo pool[]) {
		StringBuffer x = new StringBuffer();
		String type = name.toString();
		ConstantPoolInfo item;

		if (type.compareTo("ConstantValue") == 0) {
			item = pool[indexFromBytes(data)];
			return (item.toString());
		} else if (type.compareTo("SourceFile") == 0) {
			item = pool[indexFromBytes(data)];
			return (item.toString());
		} else {
			x.append(type + "<" + data.length + " bytes>");
		}
		return (x.toString());
	}

	public String toBoolean(ConstantPoolInfo pool[]) {
		ConstantPoolInfo item = pool[indexFromBytes(data)];

		if (item.intValue == 0)
			return ("= false");
		return ("= true");
	}

	public String toString() {
		return (name.toString() + " <" + data.length + " bytes>");
	}
}

/*
 * @(#)FieldInfo.java 1.3 95/08/16 Chuck McManis
 *
 * Copyright (c) 1996 Chuck McManis, All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software and its
 * documentation for NON-COMMERCIAL purposes and without fee is hereby granted
 * provided that this copyright notice appears in all copies.
 *
 * CHUCK MCMANIS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. CHUCK MCMANIS SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR
 * ITS DERIVATIVES.
 */

/**
 * This class defines a FieldInfo in the class file. Fields are used to describe
 * instance variables in a class. The toString() method is augmented by a
 * version that takes an array of ConstantPoolInfo objects (a constant pool).
 * When a constant pool is available the toString() method generates a
 * declaration for the field as it would appear in Java source.
 *
 * @version 1.3, 16 Aug 1995
 * @author Chuck McManis
 * @see ClassFile
 */

class FieldInfo {
	short accessFlags;
	ConstantPoolInfo name;
	ConstantPoolInfo signature;
	AttributeInfo attributes[];

	public boolean read(DataInputStream di, ConstantPoolInfo pool[]) throws IOException {
		int count;

		accessFlags = di.readShort();
		name = pool[di.readShort()];
		signature = pool[di.readShort()];
		count = di.readShort();
		if (count != 0) {
			attributes = new AttributeInfo[count];
			for (int i = 0; i < count; i++) {
				attributes[i] = new AttributeInfo();
				if (!attributes[i].read(di, pool))
					return (false);
			}
		}
		return (true);
	}

	public void write(DataOutputStream dos, ConstantPoolInfo pool[]) throws IOException, Exception {
		dos.writeShort(accessFlags);
		dos.writeShort(ConstantPoolInfo.indexOf(name, pool));
		dos.writeShort(ConstantPoolInfo.indexOf(signature, pool));
		if (attributes == null) {
			dos.writeShort(0);
		} else {
			dos.writeShort(attributes.length);
			for (int i = 0; i < attributes.length; i++) {
				attributes[i].write(dos, pool);
			}
		}
	}

	public String toString() {
		StringBuffer x = new StringBuffer();

		x.append(ClassFile.accessString(accessFlags));
		x.append(ClassFile.typeString(signature.toString(), name.toString()));
		if (attributes != null) {
			x.append(" = " + attributes[0].toString());
		}
		return (x.toString());
	}

	public String toString(ConstantPoolInfo pool[]) {
		StringBuffer x = new StringBuffer();
		String mytype;

		x.append(ClassFile.accessString(accessFlags));
		mytype = ClassFile.typeString(signature.toString(), name.toString());
		x.append(mytype);
		if (attributes != null) {
			if (mytype.startsWith("boolean")) {
				x.append(" " + attributes[0].toBoolean(pool));
			} else
				x.append(" " + attributes[0].toString(pool));
		}
		return (x.toString());
	}
}
