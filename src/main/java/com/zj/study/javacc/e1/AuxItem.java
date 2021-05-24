package com.zj.study.javacc.e1;

public class AuxItem {
	String name;
	String op;
	String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AuxItem [name=" + name + ", op=" + op + ", value=" + value + "]";
	}
}
