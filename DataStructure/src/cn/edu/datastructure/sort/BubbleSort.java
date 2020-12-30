package cn.edu.datastructure.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        bubbleSort(arr);

    }
    public static void bubbleSort(int[] arr){

        for (int i = 0; i < arr.length-1; i++) {
            boolean flag = false;//用来判断是否产生了交换

            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j] > arr[j+1]){
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"次交换后的结果为：");
            System.out.println(Arrays.toString(arr));

            if(!flag){//如果flag为false就退出。
                break;
            }
        }

    }
}
