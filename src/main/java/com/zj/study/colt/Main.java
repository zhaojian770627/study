package com.zj.study.colt;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

public class Main {

	public static void main(String[] args) {
//		DoubleMatrix2D matrix;
//		matrix = new DenseDoubleMatrix2D(2, 2);
//		matrix.set(0, 0, 11);
//		matrix.set(0, 1, -1);
//
//		matrix.set(1, 0, -2);
//		matrix.set(1, 1, 12);
//
//		DoubleMatrix2D D;
//		D = new DenseDoubleMatrix2D(2, 1);
//		D.set(0, 0, 1500);
//		D.set(1, 0, 2000);
//		Algebra algebra = new Algebra();
//		DoubleMatrix2D X = algebra.solve(matrix, D);
//		System.out.println(X);
		
		DoubleMatrix2D matrix;
		matrix = new DenseDoubleMatrix2D(6, 6);
		DoubleMatrix2D D;
		D = new DenseDoubleMatrix2D(6, 1);
		setValue(matrix, D);
		Algebra algebra = new Algebra();
		DoubleMatrix2D X = algebra.solve(matrix, D);
		System.out.println(X);
	}
	
	private static void setValue(DoubleMatrix2D matrix,DoubleMatrix2D D) {
		double[][] test = new double[][] { 
			{ 1, 0, 0, 1, 0, -1 }, 
			{ 0, 1, 0, -1, 1, 0 }, 
			{ 0, 0, 1, 0, -1, 1 },
			{ 0.3, 0, 0, -0.7, 0, -.3 },
			{ 0, 0.2, 0, -.2, -.8, 0 },
			{ 0, 0, .1, 0, -.1, -.9 }
		};
			
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 6; j++) {
				matrix.set(i, j, test[i][j]);
			}

		D.set(0, 0,100);
		D.set(1, 0,0);
		D.set(2, 0,0);
		D.set(3, 0,0);
		D.set(4, 0,0);
		D.set(5, 0,0);

	}

}
