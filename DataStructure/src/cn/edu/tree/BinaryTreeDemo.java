package cn.edu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode a = new HeroNode(1, "1");
        HeroNode b = new HeroNode(2, "2");
        HeroNode c = new HeroNode(3, "3");
        HeroNode d = new HeroNode(4, "4");
        HeroNode e = new HeroNode(5, "5");
        HeroNode f = new HeroNode(6, "6");
        HeroNode g = new HeroNode(7, "7");
        HeroNode h = new HeroNode(8, "8");
        HeroNode i = new HeroNode(9, "9");

        a.setLeftNode(b);
        a.setRightNode(c);
        b.setLeftNode(d);
        b.setRightNode(e);
        c.setLeftNode(f);
        c.setRightNode(g);
        d.setLeftNode(h);
        d.setRightNode(i);

        binaryTree.setRoot(a);

        //测试
        System.out.println("前序遍历");
        binaryTree.prefixOrder();
        binaryTree.deleteNode(4);
        System.out.println("删除结点后~~");
        binaryTree.prefixOrder();
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//        System.out.println("后序遍历");
//        binaryTree.suffixOrder();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//        System.out.println("前序遍历查找");
//        System.out.println(binaryTree.prefixSearch(6));
//        System.out.println("中序遍历查找");
//        System.out.println(binaryTree.infixSearch(9));
//        System.out.println("后序遍历查找");
//        System.out.println(binaryTree.suffixSearch(1));
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void prefixOrder(){
        if(this.root != null){
            this.root.prefixOrder();
        } else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        } else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public void suffixOrder(){
        if(this.root != null){
            this.root.suffixOrder();
        } else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    public HeroNode prefixSearch(int id){
        if(root != null){
            return root.prefixSearch(id);
        } else{
            return null;
        }
    }
    public HeroNode infixSearch(int id){
        if(root != null){
            return root.infixSearch(id);
        } else{
            return null;
        }
    }
    public HeroNode suffixSearch(int id){
        if(root != null){
            return root.suffixSearch(id);
        } else{
            return null;
        }
    }

    public void deleteNode(int id){
        if(root != null){
            if(root.getId() == id){
                root = null;
            } else{
                root.deleteNode(id);
            }
        } else{
            System.out.println("空树，无法删除！");
        }
    }
}

class HeroNode {
    private int id;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;

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

    public void deleteNode(int id){

        if(this.leftNode != null && this.leftNode.id == id){
            this.leftNode = null;
            return;
        }
        if(this.rightNode != null && this.rightNode.id == id){
            this.rightNode = null;
            return;
        }
        if(this.leftNode != null){
            this.leftNode.deleteNode(id);
        }
        if(this.rightNode != null){
            this.rightNode.deleteNode(id);
        }
    }

    /**
     * 编写前序遍历
     */
    public void prefixOrder() {

        //输出父结点
        System.out.println(this);
        //递归向左子树遍历
        if (this.leftNode != null) {
            this.leftNode.prefixOrder();
        }
        //递归向右子树遍历
        if (this.rightNode != null) {
            this.rightNode.prefixOrder();
        }
    }

    /**
     * 编写中序遍历
     */
    public void infixOrder() {
        //递归向左子树遍历
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        //输出父结点
        System.out.println(this);
        //递归向右子树遍历
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    /**
     * 编写后序遍历
     */
    public void suffixOrder() {
        //递归向左子树遍历
        if (this.leftNode != null) {
            this.leftNode.suffixOrder();
        }
        //递归向右子树遍历
        if (this.rightNode != null) {
            this.rightNode.suffixOrder();
        }
        //输出父结点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @return
     */
    public HeroNode prefixSearch(int id){
        System.out.println("xxxxxx");
        //比较当前结点
        if(this.id == id){
            return this;
        }
        HeroNode rtNode = null;
        if(this.leftNode != null){
            rtNode = this.leftNode.prefixSearch(id);
        }
        if(rtNode != null){
            return rtNode;
        }
        if(this.rightNode != null){
            rtNode = this.rightNode.prefixSearch(id);
        }

        return rtNode;
    }

    /**
     * 中序遍历查找
     * @return
     */
    public HeroNode infixSearch(int id){
        HeroNode rtNode = null;
        if(this.leftNode != null){
            rtNode = this.leftNode.infixSearch(id);
        }
        if(rtNode != null){
            return rtNode;
        }
        System.out.println("xxxxxx");
        if(this.id == id){
            return this;
        }

        if(this.rightNode != null){
            rtNode = this.rightNode.infixSearch(id);
        }
        return rtNode;
    }

    /**
     * 后序遍历查找
     * @return
     */
    public HeroNode suffixSearch(int id){
        HeroNode rtNode = null;
        if(this.leftNode != null){
            rtNode = this.leftNode.suffixSearch(id);
        }
        if(rtNode != null){
            return rtNode;
        }

        if(this.rightNode != null){
            rtNode = this.rightNode.suffixSearch(id);
        }
        if(rtNode != null){
            return rtNode;
        }
        System.out.println("xxxxxx");
        if(this.id == id){
            return this;
        }

        return rtNode;
    }
}