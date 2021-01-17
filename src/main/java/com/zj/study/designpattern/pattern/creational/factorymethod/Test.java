package com.zj.study.designpattern.pattern.creational.factorymethod;

/**
 * Created by geely
 * 
 * 1.Collection Iterable 
 * 可以认为Iterable是个产品  
 * 具体的Collection的实现类是个工厂，创建自己的Iterable
 * 
 * 2.URLStreamHandlerFactory
 * 
 */
public class Test {
	public static void main(String[] args) {
		VideoFactory videoFactory = new PythonVideoFactory();
		VideoFactory videoFactory2 = new JavaVideoFactory();
		VideoFactory videoFactory3 = new FEVideoFactory();
		Video video = videoFactory.getVideo();
		video.produce();

	}

}
