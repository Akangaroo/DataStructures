package cn.edu.datastructure.serch;

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 66);
        System.out.println("index = " + i);
    }

    /**
     * @param arr   传入的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param value 查找的值
     * @return
     */
    public static int insertValueSearch(int[] arr, int left, int right, int value) {
        if (left > right || value < arr[0] || value > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (value - arr[left]) / (arr[right] - arr[left]);
        int midValue = arr[mid];

        if (midValue < value) {//说明向右递归
            return insertValueSearch(arr, mid + 1, right, value);
        } else if (midValue > value) {
            return insertValueSearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }
}
