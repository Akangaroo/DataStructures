package cn.edu.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试~~~");
        HeroNode2 a = new HeroNode2(1, "a");
        HeroNode2 b = new HeroNode2(2, "b");
        HeroNode2 c = new HeroNode2(3, "c");
        HeroNode2 d = new HeroNode2(4, "d");

        DoubleLinkedList dbList = new DoubleLinkedList();
        dbList.add(a);
        dbList.add(b);
        dbList.add(c);
        dbList.add(d);
        dbList.list();
        System.out.println("=========update=========");
        HeroNode2 newHero = new HeroNode2(4, "hahaha");
        dbList.update(newHero);
        dbList.list();
        System.out.println("=========delete=========");
        dbList.delete(4);
        dbList.list();
    }
}
class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"");


    //返回头结点
    public HeroNode2 getHead(){
        return head;
    }

    //添加到双向链表的尾部
    //添加结点到链表
    public void add(HeroNode2 newheroNode){
        //因为头结点不能动，需要一个辅助指针temp
        HeroNode2 temp = head;
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
        //形成一个双向链表
        temp.next = newheroNode;
        newheroNode.pre = temp;
    }

    //根据id来修改
    public void update(HeroNode2 newnode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的结点
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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
    //可以直接找到需要删除的结点，找到后自我删除即可
    public void delete(int id){

        //判断当前链表是否为空
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到待删除结点
        while(true){
            if(temp == null){
                break;
            }
            if(temp.id == id){
                //找到待删除结点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            //如果是最后一个结点就不用执行下面这句话，否则会出现空指针异常。
            if(temp.next != null){
                temp.next.pre = temp.pre;

            }
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
        HeroNode2 temp = head.next;
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
class HeroNode2{
    public int id;
    public String name;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int id, String name) {
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
