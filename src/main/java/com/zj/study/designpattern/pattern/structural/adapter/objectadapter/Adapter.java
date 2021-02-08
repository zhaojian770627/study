package com.zj.study.designpattern.pattern.structural.adapter.objectadapter;

/**
 * Created by geely
 * 
 * 对象适配器模式
 */
public class Adapter implements Target{
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        //...
        adaptee.adapteeRequest();
        //...
    }
}
