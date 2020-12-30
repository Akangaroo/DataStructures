package cn.edu.datastructure.sort;


import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    //编写一个堆排序的方法
    public static void heapSort(int[] arr) {
        System.out.println("堆排序！");

        buildMaxHeap(arr);

        int len = arr.length;
        // 交换堆顶和当前末尾的节点，重置大顶堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
//            adjustHeap(arr,0,i);
            len--;
            heapadj(arr, 0, len);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 构建一个大顶堆
     * @param arr
     */
    public static void buildMaxHeap(int[] arr){
        for (int i = arr.length/2 - 1; i >= 0 ; i--) {
            //adjustHeap(arr,i,arr.length);
            heapadj(arr, i, arr.length);
        }
    }

    //将一个数组（二叉树）调整成一个大顶堆

    /**
     * @param arr    待调整的数组
     * @param i      表示非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整    length是在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素的值，保存在临时变量
        //开始调整
        //1. k = i+2+1  k是i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//说明左子结点的值小于右子结点的值
                k++;
            }
            if (arr[k] > temp) {//如果子结点大于父结点
                arr[i] = arr[k];//把较大的值赋值给当前结点
                i = k;//i指向k，继续循环比较
            } else {
                break;
            }
        }
        //当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶（局部）
        arr[i] = temp;//将temp放到调整后的位置
    }

    public static void heapadj(int[] arr, int i, int length) {
        //先找出当前结点的左右结点
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        //默认当前结点是最大值
        int largestIndex = i;
        if (left < length && arr[left] > arr[largestIndex]) {
            largestIndex = left;
        }
        if (right < length && arr[right] > arr[largestIndex]) {
            largestIndex = right;
        }
        if (largestIndex != i) {
            // 如果最大值不是当前非叶子节点的值,就交换
            swap(arr, i, largestIndex);
            // 因为互换之后，子节点的值变了，如果该子节点也有自己的子节点，仍需要再次调整。
            heapadj(arr, largestIndex, length);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}


