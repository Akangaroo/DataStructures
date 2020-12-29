package cn.edu.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node1 = new HeroNode(3, "jack");
        HeroNode node2 = new HeroNode(6, "smith");
        HeroNode node3 = new HeroNode(8, "mary");
        HeroNode node4 = new HeroNode(10, "nancy");
        HeroNode node5 = new HeroNode(14, "randy");

        root.setLeftNode(node1);
        root.setRightNode(node2);
        node1.setLeftNode(node3);
        node1.setRightNode(node4);
        node2.setLeftNode(node5);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNode();

        //以10号结点测试
        HeroNode leftNode = node4.getLeftNode();
        HeroNode rightNode = node4.getRightNode();
        System.out.println("10号结点的前驱结点："+leftNode);
        System.out.println("10号结点的后继结点："+rightNode);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();


    }
}
class ThreadedBinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    private HeroNode preNode = null;

    //编写对二叉树中序线索化的方法
    public void threadedNode(){
        this.threadedNode(root);
    }
    /**
     *
     * @param node      当前需要线索化的结点
     */
    public void threadedNode(HeroNode node){
        //如果node==null，无法线索化
        if(node == null){
            return;
        }

        //1. 先线索化左子树
        threadedNode(node.getLeftNode());
        //2. 线索化当前结点
        //2.1 先处理当前结点的前驱结点
        if(node.getLeftNode() == null){
            //让当前结点的左指针指向前驱结点
            node.setLeftNode(preNode);
            //修改当前左指针的类型,指向的是前驱结点
            node.setLeftType(1);
        }
        //2.2 处理后继结点
        if(preNode != null && preNode.getRightNode() == null){
            //让前驱结点的右指针指向当前结点
            preNode.setRightNode(node);
            //修改前驱结点的右指针类型
            preNode.setRightType(1);
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        preNode = node;
        //3. 再线索化右子树
        threadedNode(node.getRightNode());

    }

    //遍历线索化中序二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while(node != null){
            //循环找到的leftType等于1的结点，第一个找到的就是8结点
            //后面随着遍历变化，因为当leftType==1时，说明该结点是按照线索化处理后的有效节点
            while(node.getLeftType() == 0){
                node = node.getLeftNode();
            }
            //打印当前结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while(node.getRightType() == 1){
                //获取到当前结点的后继结点
                node = node.getRightNode();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node = node.getRightNode();
        }
    }

}

//创建HeroNode
class HeroNode {
    private int id;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;
    //说明：
    //1. leftType为0，表示指向的是左子树，如果1表示指向的是前驱结点
    //2. rightType为0，表示指向的是右子树，如果1表示指向的是后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HeroNode leftNode) {
        this.leftNode = leftNode;
    }

    public HeroNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HeroNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}