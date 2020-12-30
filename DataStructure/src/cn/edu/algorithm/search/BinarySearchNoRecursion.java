package cn.edu.algorithm.search;

/**
 * @author kangyouwei
 * @version v1.0.0
 * @date 2020/12/29 16:13
 **/
public class BinarySearchNoRecursion {
    public static void main(String[] args) {
        int[] array = {1, 6, 17, 28, 47, 66, 99};
        int result = binarySearch(array, 6);
        System.out.println("index: " + result);
    }

    /**
     * 二分查找
     *
     * @param array  查找的数组
     * @param target 目标对象
     * @return -1代表没有返回
     */
    public static int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1; // 需要向左边查找
            } else {
                left = mid + 1; // 需要向右边查找
            }
        }
        return -1;
    }
}
