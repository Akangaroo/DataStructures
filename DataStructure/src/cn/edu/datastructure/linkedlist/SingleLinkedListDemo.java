package cn.edu.datastructure.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode a = new HeroNode(1, "a");
        HeroNode b = new HeroNode(2, "b");
        HeroNode c = new HeroNode(3, "c");
        HeroNode d = new HeroNode(4, "d");
        HeroNode e = new HeroNode(5, "e");
        HeroNode f = new HeroNode(6, "f");
        HeroNode g = new HeroNode(7, "g");
        HeroNode h = new HeroNode(8, "h");
        SingleLinkedList s = new SingleLinkedList();
        SingleLinkedList s2 = new SingleLinkedList();

        s.add(a);
        s.add(b);
        s.add(f);
        s2.add(c);
        s2.add(d);
        s2.add(e);
        s2.add(g);
        s2.add(h);

        s.list();
        System.out.println();
        s2.list();
        System.out.println();
        Merge(s.getHead(), s2.getHead());
        s.list();



//        s.addByOrder(a);
//        s.addByOrder(d);
//        s.addByOrder(b);
//        s.addByOrder(c);
//        s.addByOrder(c);


/*
        System.out.println("修改前的链表：");
        s.list();*/



/*        System.out.println("逆序打印：");
        reversePrint(s.getHead());

        System.out.println("反转后的链表：");
        reverseList(s.getHead());
        s.list();*/

/*        //修改结点
        HeroNode b2 = new HeroNode(2, "二号");
        s.update(b2);
        System.out.println("修改后的链表：");
        s.list();
        System.out.println(getLength(s.getHead()));

        //删除结点
        s.delete(1);
        s.delete(4);
        System.out.println("删除结点后为：");
        s.list();
        System.out.println(getLength(s.getHead()));

        System.out.println("倒数第二个结点为：");
        HeroNode lastIndex = findLastIndex(s.getHead(), 2);
        System.out.println(lastIndex);*/
    }

    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        //定义一个辅助变量
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;
    }


    //查找单链表的倒数第k个结点
    //1. index，表示是倒数index个结点
    //2. 先把链表从头到尾遍历，得到链表的总的长度getLength
    //3. 得到size后，我们从链表的第一个开始遍历size-index个
    public static HeroNode findLastIndex(HeroNode head,int index){
        if(head.next == null){
            return null;
        }
        int size = getLength(head);
        //先做一个index的校验
        if(index <=0 || index > size){
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < size - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    //单链表的反转
    //1. 创建新链表，头结点插入法反转
    public static void reverseList(HeroNode head){
        //如果链表为空或者链表只要一个，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }

        HeroNode pCur = head.next;//当前结点
        HeroNode pNext = null;//下一个结点
        HeroNode reverseHead = new HeroNode(0,"");

        while(pCur != null){
            pNext = pCur.next;
            pCur.next = reverseHead.next;//将reverse的指向赋值给pCur
            reverseHead.next = pCur;
            pCur = pNext;
        }
        head.next = reverseHead.next;
    }

    //逆序打印
    public static void reversePrint(HeroNode head){
        if(head.next == null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;

        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        while(stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    public static void Merge(HeroNode head1,HeroNode head2){
        if(head1 == null || head2 == null){
            return;
        }

        HeroNode temp1 = head1.next;
        HeroNode temp2 = head2.next;

        HeroNode temp3 = temp1.id < temp2.id ? head1:head2;

        while(temp1 != null && temp2 != null){//如果不为空

            if(temp1.id <= temp2.id){
                temp3.next = temp1;//将temp3的下一个指向为temp1
                temp3 = temp1;//重新定义temp3的值
                temp1 = temp1.next;

            }else{
                temp3.next = temp2;
                temp3 = temp2;
                temp2 = temp2.next;

            }
        }

        temp3.next = (temp1==null)?temp2:temp1;

    }
}
class SingleLinkedList{
    //定义一个头结点
    HeroNode head = new HeroNode(0,"");

    public HeroNode getHead() {
        return head;
    }

    //添加结点到链表
    public void add(HeroNode heroNode){
        //因为头结点不能动，需要一个辅助指针temp
        HeroNode temp = head;
        //遍历链表，找到最后
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到就往后移
            temp = temp.next;
        }
        //当退出while循环的时候，temp就指向了链表的最后
        //将最后的这个结点的next，指向新的结点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        //找到temp是位于添加位置的前一个结点
        HeroNode temp = head;
        boolean flag = false;//判断添加的结点是否存在
        while(true){
            if(temp.next == null){//temp在链表的最后
                break;
            }
            if(temp.next.id > heroNode.id){//位置找到，就在temp的后面插入
                break;
            } else if(temp.next.id == heroNode.id){//编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if(flag){
            System.out.println("准备插入的编号"+heroNode.id+"已经存在");
        }else{
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据id来修改
    public void update(HeroNode newnode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的结点
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;

        while(true){
            if(temp == null){
                break;//遍历结束
            }
            if(temp.id == newnode.id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的结点
        if(flag){
            temp.name = newnode.name;
        } else {
            System.out.println("没有找到编号等于"+newnode.id+"的结点");
        }
    }

    //删除结点
    public void delete(int id){
        HeroNode temp = head;
        boolean flag = false;//是否找到待删除结点的前一个结点
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.id == id){
                //找到待删除结点的前一个结点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.next = temp.next.next;
        } else {
            System.out.println("要删除的结点"+id+"不存在！");
        }

    }

    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头结点，不能动，需要辅助变量来遍历。
        HeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            //输出结点的信息
            System.out.println(temp);
            temp = temp.next;
        }
    }

}
/**
 * 定义一个HeroNode,每个HeroNode对象就是一个结点。
 */
class HeroNode{
    public int id;
    public String name;
    public HeroNode next;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
