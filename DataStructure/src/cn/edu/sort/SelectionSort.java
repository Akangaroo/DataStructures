package cn.edu.sort;

import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        long start = System.currentTimeMillis();
        selectionSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 尚未优化的选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            //假设arr[i]为最小的元素
            int minIndex = i;
            //找到最小元素的下标
            for (int j = i+1; j < arr.length; j++) {
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }

            }
            //交换元素
            if(minIndex != i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }

        }
    }
}
