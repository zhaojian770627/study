package com.zj.study.designpattern.pattern.structural.adapter.classadapter;

/**
 * Created by geely
 * 
 * 类适配器模式
 */
public class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        //...
        super.adapteeRequest();
        //...
    }
}
