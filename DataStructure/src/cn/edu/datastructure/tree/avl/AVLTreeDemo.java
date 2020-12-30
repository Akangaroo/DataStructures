package cn.edu.datastructure.tree.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历：");
        avlTree.infixOrder();

        System.out.println("在没有做旋转之前~~");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("左子树的高度=" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度=" + avlTree.getRoot().rightHeight());
    }
}

//创建AVLTree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，无法遍历");
        }
    }
}

//创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    //返回以该结点为根结点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转的方法
    public void leftRotate(){
        //创建新的结点，以当前根结点的值创建
        Node newNode = new Node(value);
        //把新的结点的左子树设置为当前结点的左子树
        newNode.left = this.left;
        //把新的结点的右子树设置为当前结点的右子树的左子树
        newNode.right = this.right.left;
        //把当前结点的值替换成右子结点的值
        this.value = this.right.value;
        //把当前结点的右子树设置为当前结点的右子树的右子树
        this.right = this.right.right;
        //把当前结点的左子结点设置为新的结点
        this.left = newNode;

    }

    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点的代码
    //递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }

        //传入结点的值，和当前子树根结点的关系
        if (node.value < this.value) {
            //如果当前结点左子结点为空
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子结点添加
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        //当添加完结点后，如果右子树的高度-左子树的高度>1
        if(rightHeight() - leftHeight() > 1){
            if(right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();//左旋转
            return;
        }

        if(leftHeight() - rightHeight() > 1){
            //右旋转
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左子树进行左旋转
                left.leftRotate();
            }
            rightRotate();//右旋转
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.print(this.value + "\t");
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}