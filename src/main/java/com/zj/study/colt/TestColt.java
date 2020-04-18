package com.zj.study.colt;

import java.io.IOException;
import java.util.Random;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

/**
 * 
 * 耗费时间:383797 耗费内存:3808k
 * 
 * @author zhaojianc
 *
 */
public class TestColt {

	public static void main(String[] args) throws IOException {
		Runtime runtime = Runtime.getRuntime();
		long startMem = runtime.freeMemory();
		int size = 10000;
		System.out.println("Please enter a key to compute:");
		System.in.read();
		Random r = new Random();
		// 生成随机的系数矩阵100个
		DenseDoubleMatrix2D matrix = new DenseDoubleMatrix2D(size, size);
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {

				matrix.set(i, j, r.nextInt(1000000) / 100);

			}

		// 生成X
		DenseDoubleMatrix2D x = new DenseDoubleMatrix2D(size, 1);
		for (int i = 0; i < size; i++) {
			double v = r.nextInt(100) / 10000.0;
			x.set(i, 0, v);
		}

		// 矩阵相乘,得出结果矩阵
		Algebra algebra = new Algebra();
		DoubleMatrix2D D = algebra.mult(matrix, x);
		// help GC
		x = null;
		System.out.println("Start to Compute...");
		long start = System.currentTimeMillis();
		DoubleMatrix2D xr = algebra.solve(matrix, D);
		long end = System.currentTimeMillis();
		long orz = startMem - runtime.freeMemory();
		System.out.println("耗费时间:" + (end - start));
		System.out.println("耗费内存:" + orz / 1024 + "k");
		System.out.println("Please enter a key to end:");
		System.in.read();
	}

}
