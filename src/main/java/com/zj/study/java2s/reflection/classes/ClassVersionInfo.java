package com.zj.study.java2s.reflection.classes;

import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.net.URL;
import java.security.CodeSource;

/**
 * Encapsulates a class serialVersionUID and codebase.
 * 
 * @author Scott.Stark@jboss.org
 * @version $Revision: 81038 $
 */
public class ClassVersionInfo implements Serializable {
	static final long serialVersionUID = 2036506209171911437L;

	/** The named class serialVersionUID as returned by ObjectStreamClass */
	private long serialVersion;

	/** The binary class name */
	private String name;

	private boolean hasExplicitSerialVersionUID;

	private transient URL location;

	public ClassVersionInfo(String name, ClassLoader loader) throws ClassNotFoundException {
		this.name = name;
		Class c = loader.loadClass(name);
		CodeSource cs = c.getProtectionDomain().getCodeSource();
		if (cs != null)
			location = cs.getLocation();
		if (c.isInterface() == false) {
			ObjectStreamClass osc = ObjectStreamClass.lookup(c);
			if (osc != null) {
				serialVersion = osc.getSerialVersionUID();
				try {
					c.getDeclaredField("serialVersionUID");
					hasExplicitSerialVersionUID = true;
				} catch (NoSuchFieldException e) {
					hasExplicitSerialVersionUID = false;
				}
			}
		}
	}

	public long getSerialVersion() {
		return serialVersion;
	}

	public boolean getHasExplicitSerialVersionUID() {
		return hasExplicitSerialVersionUID;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		StringBuffer tmp = new StringBuffer("ClassVersionInfo");
		tmp.append('{');
		tmp.append("serialVersion=");
		tmp.append(serialVersion);
		tmp.append(", hasExplicitSerialVersionUID=");
		tmp.append(hasExplicitSerialVersionUID);
		tmp.append(", name=");
		tmp.append(name);
		tmp.append(", location=");
		tmp.append(location);
		tmp.append('}');
		return tmp.toString();
	}

	/**
	 * Usage: ClassVersionInfo class-name
	 * 
	 * Locate the class name on the thread context class loader classpath and print
	 * its version info.
	 * 
	 * @param args
	 *            [0] = class-name
	 */
	public static void main(String[] args) throws Exception {
		if (args.length == 0)
			throw new IllegalStateException("Usage: ...ClassVersionInfo class-name");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		ClassVersionInfo info = new ClassVersionInfo(args[0], loader);
		System.out.println(info);
	}
}
