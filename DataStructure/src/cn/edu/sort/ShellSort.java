package cn.edu.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        System.out.println("原先的数组~~");
        System.out.println(Arrays.toString(arr));
        shellSort2(arr);
    }

    public static void shellSort(int[] arr){

        int temp;
        int count = 0;
        for (int gap = arr.length/2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {
                //以下其实就是一个插入排序
                for (int j = i-gap; j >= 0 ; j -= gap) {
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
            System.out.println("第"+(++count)+"次希尔排序的结果~~");
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void shellSort2(int[] arr){

        int temp;
        int count = 0;
        for (int gap = arr.length/2; gap > 0; gap /= 2) {

            for (int i = gap; i < arr.length; i++) {
                //以下其实就是一个插入排序
                temp = arr[i];//定义待插入的数
                int j;

                for (j = i-gap; j >= 0 && (arr[j] > temp) ; j -= gap) {
                    arr[j+gap] = arr[j];//把大于需要插入的数往后移动。最后不大于temp的数就空出来
                }
                arr[j+gap] = temp;
            }
            System.out.println("第"+(++count)+"次希尔排序的结果~~");
            System.out.println(Arrays.toString(arr));
        }
    }
}
