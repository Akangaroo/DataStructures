package cn.edu.datastructure.serch;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibonacciSearch(arr, 89);
        System.out.println("index = " + i);
    }

    /**
     * 得到一个fibonacci数列
     *
     * @return
     */
    public static int[] fibonacci() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 编写Fibonacci查找算法
     *
     * @param arr 数组
     * @param key 需要查找的值
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;//存放我们的mid值

        int f[] = fibonacci();//获取到Fibonacci数列
        //{1,1,2,3,5,8,13,21,34,55}

        int k = 0;//表示Fibonacci分割数值的下标
        //获取到Fibonacci分割值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k] 可能大于我们数组的长度，我们需要使用一个Arrays类，构造一个新的数组，并指向arr。
        //arr原先的数组，f[k]，扩容的长度。
        int[] temp = Arrays.copyOf(arr, f[k]);

        //实际上需要使用arr数组的最后的数填充temp
        //即{1, 8, 10, 89, 1000, 1234, 0, 0}--->{1, 8, 10, 89, 1000, 1234, 1234, 1234};
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        //使用while循环处理，来查找key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//说明应该继续向左边（前面）查找
                high = mid - 1;
                //全部元素 = 前面元素 + 后面元素
                //f[k] = f[k-1] + f[k-2];
                //因为前面有k-1个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]前面继续查找
                //即下次循环mid = f[k - 1 - 1] - 1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                //需要确定返回的是哪个下标
                if (mid <= high) {
                    return mid; //若相等则说明mid即为查找到的位置
                } else {
                    return high;  //若mid>=n则说明是扩展的数值,返回n-1
                }
            }
        }
        return -1;
    }
}
