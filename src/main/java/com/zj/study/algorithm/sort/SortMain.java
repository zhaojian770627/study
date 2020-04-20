package com.zj.study.algorithm.sort;

import edu.emory.mathcs.backport.java.util.Arrays;

public class SortMain {

    public static void main(String[] args) {
        int[] a = new int[]{1, 3, 5, 7, 9, 2, 4, 6, 8, 99};
//		int[] ary = new int[] { 3, 1, 4 };
        SortMain main = new SortMain();
//		main.selectSort(a);
//		main.inserSort(a);
//      main.shellSort(a);
        a = main.mergeSort(a);
        print(a);
    }

    private static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    /**
     * 选择排序
     *
     * @param a
     */
    private void selectSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }

            int t = a[i];
            a[i] = a[min];
            a[min] = t;
        }
    }

    /**
     * 插入排序
     *
     * @param a
     */
    void inserSort(int[] a) {
        // 两层巡皇
        for (int i = 0; i < a.length; i++) {
            int j = i;
            for (; j > 0; j--) {
                if (a[i] < a[j - 1])
                    continue;
                else
                    break;
            }
            if (i != j) {
                int t = a[i];
                for (int k = i; k > j; k--) {
                    a[k] = a[k - 1];
                }
                a[j] = t;
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param a
     */
    void shellSort(int[] a) {
        int grap = a.length / 2;
        while (grap > 0) {
            innerShellSort(a, grap);
            grap = grap / 2;
        }
    }

    /**
     * @param a    数组
     * @param grap 间隙
     */
    void innerShellSort(int[] a, int grap) {
        // 两层巡皇
        for (int i = 0; i < a.length; i = i + grap) {
            int j = i;
            for (; j > 0; j = j - grap) {
                if (a[i] < a[j - grap])
                    continue;
                else
                    break;
            }

            if (i != j) {
                int t = a[i];
                for (int k = i; k > j; k = k - grap) {
                    a[k] = a[k - grap];
                }
                a[j] = t;
            }
        }
    }

    /**
     * 计数排序
     *
     * @param a
     */
    void countSort(int[] a) {

    }

    /**
     * 归并排序
     *
     * @param ary
     * @return
     */
    int[] mergeSort(int[] ary) {
        if (ary.length <= 1)
            return ary;
        int n = ary.length / 2;

        int[] left = Arrays.copyOf(ary, n);
        int[] right = Arrays.copyOfRange(ary, n, ary.length);

        int[] a = mergeSort(left);
        int[] b = mergeSort(right);

        return merge(a, b);
    }

    /**
     * 归并排序，进行归并
     *
     * @param a
     * @param b
     * @return
     */
    private int[] merge(int[] a, int[] b) {
        int[] m = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                m[k++] = a[i++];
            } else {
                m[k++] = a[j++];
            }
        }

        if (i == a.length) {
            System.arraycopy(b, j, m, k, b.length - j);
        } else {
            System.arraycopy(a, i, m, k, a.length - i);
        }

        return m;
    }
}
