package cn.edu.queue;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 数组模拟环形队列
 */
public class CircleArrayDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例");

        //创建队列
        CircleArray queue = new CircleArray(4);//最大数据为4，有效数据为3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show)，显示队列");
            System.out.println("e(exit)，退出程序");
            System.out.println("a(add)，添加数据到队列");
            System.out.println("g(get)，从队列取出数据");
            System.out.println("h(head)，查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int num = queue.getQueue();
                        System.out.println("取出的数据是：" + num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int num = queue.headQueue();
                        System.out.println("队列头的数据是：" + num);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。。。");
    }
}

class CircleArray {
    private int maxSize;//数组的最大容量
    private int front;//队列头 从0开始，指向队列的第一个元素
    private int rear;//队列尾  从0开始，指向队列的最后一个元素的下一个位置。
    private int[] arr;//该数组用于存放数据

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移
        rear = (rear + 1) % maxSize;
    }

    //数据出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据");
        }
        //front是指向队列的第一个元素
        //1. 先把队列的值保存到一个临时变量。
        //2. front后移
        //3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据！");
            return;
        }
        //从front开始遍历
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //显示队列的头部，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }
}