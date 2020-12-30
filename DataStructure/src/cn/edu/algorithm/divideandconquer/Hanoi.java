package cn.edu.algorithm.divideandconquer;

/**
 * @author kangyouwei
 * @version v1.0.0
 * @date 2020/12/29 16:44
 **/
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

    /**
     * 把a柱子上的盘子移动到c柱子
     *
     * @param num 盘子的个数
     * @param a   a柱子
     * @param b   b柱子
     * @param c   c柱子
     */
    public static void hanoi(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "-->" + c);
        } else {
            // 1. 先把a最上面的盘移动到b，移动过程会借助c
            hanoi(num - 1, a, c, b);
            // 2. 把a最下面的盘移动到c
            System.out.println("第" + num + "个盘从" + a + "-->" + c);
            // 3. 把b上面的所有的盘移动到c，借助a
            hanoi(num - 1, b, a, c);
        }
    }
}
