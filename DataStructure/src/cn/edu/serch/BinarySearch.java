package cn.edu.serch;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10};

        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 6);
        System.out.println("index = " + list);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明递归完整个数组，但是没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    /**
     * 如果有n个相同的元素，返回这n个元素的索引。
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right时，说明递归完整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();//返回空的ArrayList
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {

            ArrayList<Integer> list = new ArrayList<>();
            list.add(mid);

            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp--;//temp左移
            }

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp++;
            }

            return list;
        }
    }
}
