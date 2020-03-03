package com.zj.study.colt;

import java.util.Random;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class TestColt {

	public static void main(String[] args) {

		int size = 100;
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

		long start = System.currentTimeMillis();
		DoubleMatrix2D xr = algebra.solve(matrix, D);
		long end = System.currentTimeMillis();
		System.out.println("耗费时间:" + (end - start));
		System.out.println(x);
		System.out.println(xr);
	}

}
