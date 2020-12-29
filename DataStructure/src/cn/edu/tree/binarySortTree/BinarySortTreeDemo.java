package cn.edu.tree.binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环地添加结点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();//1	2	3	5	7	9	10	12
        System.out.println();

        //测试删除叶子结点
        binarySortTree.delNode(10);
        System.out.println("删除结点后：");
        binarySortTree.infixOrder();


    }
}
//创建二叉排序树
class BinarySortTree{
    private Node root;

    //查找要删除的结点
    public Node search(int value){
        if(root == null){
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     *
     * @param node  传入的结点（当作二叉排序树的根结点）
     * @return      返回的是以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环地查找左结点，就会找到最小值
        while(target.left != null){
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //要删除结点的父结点
    public Node searchParent(int value){
        if(root == null){
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    //删除结点
    public void delNode(int value){
        if(root == null){
            return;
        } else {
            //1. 查找要删除的结点
            Node targetNode = search(value);
            //没有找到要删除的结点
            if(targetNode == null){
                return;
            }
            //如果发现当前这棵二叉排序树只有一个结点
            if(root.left == null && root.right == null){
                root = null;
            }

            //2. 去找targetNode的父结点
            Node parent = searchParent(value);

            //3. 如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父结点的左子结点还是右子结点
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                } else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            } else if(targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {    //删除只有一颗子树的结点
                if(targetNode.left != null){
                    if(parent != null){
                        //如果要删除的targetNode有左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        } else{ //targetNode是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else{
                        root = targetNode.left;
                    }
                } else {//要删除的结点有右子结点
                    if(parent != null){
                        if (parent.left.value == value){
                            parent.left = targetNode.right;
                        } else{
                            parent.right = targetNode.right;
                        }
                    } else{
                        root = targetNode.right;
                    }

                }
            }
        }
    }

    public void add(Node node){
        if(root == null){
            root = node;
        } else{
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        } else{
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

    /**
     * 查找要删除的结点
     */
    public Node search(int value){
        if(value == this.value){
            return this;
        } else if(value < this.value){//应当向左子树递归查找
            //如果左子结点为空
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        } else{//应当向右子树递归查找
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除结点的父结点
     * @param value
     * @return          要删除结点的父结点
     */
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else{
            //如果查找的值小于当前结点的值
            //且当前结点的左子结点不为空
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            } else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父结点
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点的代码
    //递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node){
        if(node == null){
            return;
        }

        //传入结点的值，和当前子树根结点的关系
        if(node.value < this.value){
            //如果当前结点左子结点为空
            if(this.left == null){
                this.left = node;
            } else{
                //递归的向左子结点添加
                this.left.add(node);
            }
        } else{
            if(this.right == null){
                this.right = node;
            } else{
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.print(this.value+"\t");
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}