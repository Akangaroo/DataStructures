package cn.edu.datastructure.sort;


public class QuickSort {
    public static void main(String[] args) {
/*        int[] arr = {-9,29,38,0,-12,25,79,-15};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));*/

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int)(Math.random()*800000);
        }

        long start1 = System.currentTimeMillis();
        quickSort(arr,0,arr.length-1);
        long end1 = System.currentTimeMillis();
        System.out.println(end1-start1);

    }

    public static void quickSort(int[] arr,int start,int end){

        int pivotKey = arr[start];//枢轴记录关键字
        int low = start;
        int high = end;

        while(low < high){
            // 从 高位 向 低位 找第一个小于pivotKey的数
            while((low < high) && (arr[high] >= pivotKey)){
                high--;
            }
            if(low < high)
                arr[low++] = arr[high];
            //从 低位 向 高位 找第一个小于pivotKey的数
            while((low < high) && (arr[low] <= pivotKey)){
                low++;
            }
            arr[high--] = arr[low];
        }
        arr[low] = pivotKey;
        //if判断是为了防止数组越界
        if(start < low-1)   quickSort(arr,start,low-1);// 递归调用
        if(low+1 < end) quickSort(arr,low+1,end);
    }

}
