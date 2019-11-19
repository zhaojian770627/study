package com.zj.study.framework.lock;

import java.util.Random;

public class Tools {
	public static int getRandomInt(int end) {
		Random df = new Random();
		return df.nextInt(end);
	}
}
