package com.zj.study.framework.lock.single;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com 
 * 懒汉式-双重检查 
 * 
 * 这种方式是有问题的，因为内存地址可见的时候，里面不一定初始化完，必须加volatile
 * 
 */
public class SingleDcl {
    private volatile static SingleDcl singleDcl;
    private SingleDcl(){
    }

    public static SingleDcl getInstance(){
    	if(singleDcl==null) {
    		synchronized (SingleDcl.class) {//类锁
				if(singleDcl==null) {
					singleDcl = new SingleDcl();
				}
			}
    	}
        return singleDcl;
    }
}
