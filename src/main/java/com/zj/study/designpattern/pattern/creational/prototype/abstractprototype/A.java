package com.zj.study.designpattern.pattern.creational.prototype.abstractprototype;

/**
 * Created by geely
 */
public abstract class A implements Cloneable {
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
