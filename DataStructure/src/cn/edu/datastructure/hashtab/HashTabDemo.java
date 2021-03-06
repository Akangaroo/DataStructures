package cn.edu.datastructure.hashtab;

public class HashTabDemo {
    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        User user = new User(1,"hehe");
        User user1 = new User(2,"haha");
        User user2 = new User(3,"heihei");
        User user7 = new User(8,"hh");
        hashTab.add(user);
        hashTab.add(user1);
        hashTab.add(user2);
        hashTab.add(user7);
        hashTab.list();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        hashTab.delete(2);
        hashTab.list();
    }
}
class HashTab{
    private UserLinkedList[] userLinkedListArray;
    private int size;

    public HashTab(int size) {
        this.size = size;
        userLinkedListArray = new UserLinkedList[size];
        //!!!
        //初始化每个链表
        for (int i = 0; i < size; i++) {
            userLinkedListArray[i] = new UserLinkedList();
        }
    }

    public void add(User user){
        int num = hashFun(user.id);
        userLinkedListArray[num].add(user);
    }

    public void list(){
        for (int i = 0; i < size; i++) {
            userLinkedListArray[i].list(i);
        }
    }

    public void findUserById(int id){
        int num = hashFun(id);
        User user = userLinkedListArray[num].findUserById(id);
        if(user != null){
            System.out.println("在第"+num+"条链表中找到该用户为"+user);
        } else{
            System.out.println("没有找到用户");
        }
    }

    public void delete(int id){
        int num = hashFun(id);
        userLinkedListArray[num].delete(id);
    }

    public int hashFun(int id){
        return id%size;
    }

}

class UserLinkedList{
    private User head = null;


    public void add(User user){
        if(head == null){
            head = user;
            return;
        }
        User curUser = head;
        while(true){
            if(curUser.next == null){//说明链表已经到最后
                break;
            }
            curUser = curUser.next;
        }
        curUser.next = user;
    }

    public void list(int num){
        if(head == null){
            System.out.println("第"+num+"条为空~~");
            return;
        }
        System.out.print("第"+num+"条的信息为：");
        User curUser = head;
        while(true){
            System.out.print("-->{id = "+curUser.id+", name = "+ curUser.name+"}");
            if(curUser.next == null){//说明已经到最后的结点
                break;
            }
            curUser = curUser.next;//后移
        }
        System.out.println();
    }

    public User findUserById(int id){
        //先判断链表是否为空
        if (head == null) {
            System.out.println("链表为空~~~~");
            return null;
        }
        User curUser = head;
        while(true){
            if(curUser.id == id){//找到
                break;
            }
            if(curUser.next == null){//没找到
                curUser = null;
                break;
            }
            curUser = curUser.next;
        }
        return curUser;
    }

    public void delete(int id){
        if(head == null){
            System.out.println("链表为空~~无法删除~~");
            return;
        } else if(head.id == id){
            head = head.next;
            return;
        }
        User curUser = head;
        while(true){
            if(curUser.next.id == id){//找到该用户
                curUser.next = curUser.next.next;
                break;
            }
            if(curUser.next == null){
                System.out.println("没有找到该用户，无法删除~~");
                break;
            }
            curUser = curUser.next;
        }
    }
}

class User{
    public int id;
    public String name;
    public User next;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
