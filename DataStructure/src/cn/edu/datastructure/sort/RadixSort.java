package cn.edu.datastructure.sort;

import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,4,3,213,23,15,62,789};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr){
        //1. 得到数组中最大位数的个数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(max < arr[i]) max = arr[i];
        }
        int maxLength = (""+max).length();

        //2. 定义一个二维数组，每个桶就是一个一维数组
        int[][] bucket = new int[10][arr.length];

        //3. 记录每个桶中实际存放的数据个数
        int[] bucketCounts = new int[10];

        for (int i = 0 , n = 1; i < maxLength; i++ , n *= 10) {//总共循环的次数
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素对应位数的值
                int digit = arr[j] / n % 10;
                //放入桶中
                bucket[digit][bucketCounts[digit]] = arr[j];
                bucketCounts[digit]++;
            }

            int index = 0;
            //按照这个桶的顺序（桶的下标按序取出）
            for (int k = 0; k < bucketCounts.length; k++) {
                if(bucketCounts[k]!=0){
                    for (int j = 0; j < bucketCounts[k]; j++) {
                        //取出元素放入arr中
                        arr[index++] = bucket[k][j];
                    }
                }
                bucketCounts[k] = 0;
            }

        }

    }
}
