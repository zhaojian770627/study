package com.zj.study.colt;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class Main {

	public static void main(String[] args) {
		DoubleMatrix2D matrix;
		matrix = new DenseDoubleMatrix2D(2, 2);
		matrix.set(0, 0, 2);
		matrix.set(0, 1, 3);

		matrix.set(1, 0, 3);
		matrix.set(1, 1, 4);

		DoubleMatrix2D D;
		D = new DenseDoubleMatrix2D(2, 1);
		D.set(0, 0, 7);
		D.set(1, 0, 8);
		Algebra algebra = new Algebra();
		DoubleMatrix2D X = algebra.solve(matrix, D);
		System.out.println(X);
	}

}
