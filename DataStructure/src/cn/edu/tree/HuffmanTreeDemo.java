package cn.edu.tree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int [] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        //测试
        prefixOrder(root);
    }

    //编写一个前序遍历的方法
    public static void prefixOrder(Node root){
        if(root != null){
            root.prefixOrder();
        }else{
            System.out.println("空树，无法遍历！");
        }
    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr){
        //1. 遍历arr数组
        //2. 将arr的每个元素构成一个个Node
        //3. 将Node放入ArrayList中
        ArrayList<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while(nodes.size() > 1){
            //排序，从小到大
            Collections.sort(nodes);

            //1. 取出根结点权值最小的二叉树
            Node leftNode = nodes.get(0);
            //2. 取出根节点权值次小的二叉树
            Node rightNode = nodes.get(1);
            //3. 构建一颗新的二叉树
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4. 从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5. 将parent加入
            nodes.add(parent);
        }

        //返回Huffman树的最后一个结点
        return nodes.get(0);
    }


}
//创建结点类
//为了使Node对象排序，让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;
    Node left;  //指向左子结点
    Node right; //指向右子节点

    //前序遍历
    public void prefixOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.prefixOrder();
        }
        if(this.right != null){
            this.right.prefixOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大进行排序
        return this.value - o.value;
    }
}