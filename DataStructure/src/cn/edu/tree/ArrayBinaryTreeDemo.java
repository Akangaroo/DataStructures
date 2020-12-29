package cn.edu.tree;

public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        arrayBinaryTree.prefixOrder();
        System.out.println();
        arrayBinaryTree.infixOrder();
        System.out.println();
        arrayBinaryTree.suffixOrder();
    }
}

class ArrayBinaryTree {
    private int[] arr;  //存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历

    public void prefixOrder() {
        this.prefixOrder(0);
    }

    /**
     * @param index 数组的下标
     */
    public void prefixOrder(int index) {
        //如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.print(arr[index] + "\t");
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            prefixOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            prefixOrder(index * 2 + 2);
        }
    }

    public void infixOrder(){
        this.infixOrder(0);
    }
    /**
     * @param index 数组的下标
     */
    public void infixOrder(int index) {
        //如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            prefixOrder(index * 2 + 1);
        }
        //输出当前这个元素
        System.out.print(arr[index]+"\t");
        if (index * 2 + 2 < arr.length) {
            prefixOrder(index * 2 + 2);
        }
    }

    public void suffixOrder(){
        this.suffixOrder(0);
    }
    /**
     * @param index 数组的下标
     */
    public void suffixOrder(int index) {
        //如果数组为空，或者arr.length == 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if (index * 2 + 1 < arr.length) {
            prefixOrder(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            prefixOrder(index * 2 + 2);
        }
        //输出当前这个元素
        System.out.print(arr[index]+"\t");
    }
}