package cn.edu.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8,7,1,3,2,4,6,5};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr,int start,int end){

        if(start < end){
            int mid = (start + end)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,start,mid);
            //向右递归进行分解
            mergeSort(arr,mid+1,end);
            //排序
            merge(arr,start,mid,end);

        }
    }

    public static void merge(int[] arr,int start,int mid,int end){
        int[] temp = new int[arr.length];
        int i = start;
        int j = mid + 1;
        
        int t = 0;//指向temp数组的当前索引

        while(i <= mid && j <= end){
            if(arr[i] <= arr[j]){
                temp[t++] = arr[i++];
            } else{
                temp[t++] = arr[j++];
            }
        }
        
        while(i <= mid){
            temp[t++] = arr[i++];
        }
        while(j <= end){
            temp[t++] = arr[j++];
        }

        //将temp数组的元素拷贝到arr
        for (int k = 0; k < t; k++) {
            arr[k+start] = temp[k];
        }
        System.out.println("start = "+start+",end = "+end);
    }
}
