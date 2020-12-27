package com.zj.study.framework.dubbox;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.cluster.LoadBalance;

public class DubboxMain {

	public static void main(String[] args) {
		LoadBalance balance = ExtensionLoader.getExtensionLoader(LoadBalance.class).getAdaptiveExtension();
		System.out.println(balance);
	}

}
