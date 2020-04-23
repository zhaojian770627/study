package com.zj.study.algorithm.sort;

public class SortMain {

    public static void main(String[] args) {
        int[] a = new int[]{4, 3, 5, 7, 9, 2, 1, 6, 8, 99};
        SortMain main = new SortMain();
        // main.selectSort(a);
        // main.insertSort(a);
        // main.shellSort(a);
//        main.mergeSort(a);
        main.quickSort(a, 0, a.length - 1);
        print(a);
    }

    private static void print(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
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
    void insertSort(int[] a) {
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
    void mergeSort(int[] ary) {
        mergeSort(ary, 0, ary.length - 1);
    }

    void mergeSort(int[] ary, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(ary, start, mid);
            mergeSort(ary, mid + 1, end);
            merge(ary, start, mid, end);
        }
    }

    private void merge(int[] a, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int p1 = start;
        int p2 = mid + 1;
        int k = 0;

        while (p1 <= mid && p2 <= end) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }

        if (p1 <= mid)
            System.arraycopy(a, p1, tmp, k, mid - p1 + 1);

        if (p2 <= end)
            System.arraycopy(a, p2, tmp, k, end - p2 + 1);

        System.arraycopy(tmp, 0, a, start, end - start + 1);
    }

    void quickSort(int a[], int start, int end) {
        int mid = a[start];

        int i = start + 1;
        int j = end;

        while (j > i) {
            for (; i < j; i++) {
                if (a[i] > mid)
                    break;
            }

            for (; j > i; j--) {
                if (a[j] < mid)
                    break;
            }

            int t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
//        a[start] = a[j];
//        a[j] = mid;
    }
}
