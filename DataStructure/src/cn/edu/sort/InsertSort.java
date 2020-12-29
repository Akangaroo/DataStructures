package cn.edu.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
       /* int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }
        System.out.println("尚未优化的版本~~");
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end-start);

        System.out.println("优化的版本~~");
        long start1 = System.currentTimeMillis();
        insertSort2(arr);
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start1);*/

        int [] arr = {12,31,45,1,43,22};
        insertSort2(arr);
    }
    //插入排序
    public static void insertSort(int[] arr){

        for (int i = 1; i < arr.length; i++) {
            //待排元素小于有序序列的最后一个元素时，向前插入
            for (int j = i; j > 0; j--) {
                if(arr[j] < arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
//            System.out.println("经过"+i+"次交换后的数据为：");
//            System.out.println(Arrays.toString(arr));
        }
    }

    //插入排序优化
    /**
     * 首先上面的这个每次替换都要定义一个temp赋值需要插入的数，这样会造成不必要的浪费：
     * 所以我们可以吧所有的大于需要插入的数先保存，然后进行比较，然后将最后的正确位置空出来。
     * 把之前保存的需要插入的数放到正确位置上；
     */
    public static void insertSort2(int[] arr){

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];//定义待插入的数
            int j;
            for (j = i-1; j >= 0 && (arr[j] > temp) ; j--) {
                arr[j+1] = arr[j];//把大于需要插入的数往后移动。最后不大于temp的数就空出来
            }
            arr[j+1] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }
}
