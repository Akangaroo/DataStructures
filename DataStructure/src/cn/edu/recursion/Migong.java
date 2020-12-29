package cn.edu.recursion;

public class Migong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //先把上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //再把左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置相应的挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图的情况~~");
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯给小球找路
        setWay(map, 1, 1);

        //输出新的地图
        System.out.println("小球走过并标识的地图~~");
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //1. 出发点为(1,1),终点为(6,5)
    //2. 当地图的[i][j]为0时，表示没有走过，当为1时，表示墙。
    //3. 2表示通路可以走，3表示已经走过，但走不通。
    //4. 策略：下->右->上->左

    /**
     * 使用递归回溯来给小球找路
     *
     * @param map 地图
     * @param i   起始位置
     * @param j   起始位置
     * @return 找到路返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {

        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前的这个点还没有走过
                //按照策略：下->右->上->左

                map[i][j] = 2;//假定该点是可以走通的

                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//向左走
                    return true;
                } else {
                    //说明该点是走不通的，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { //如果map[i][j] != 0 ;可能是1，2，3
                return false;
            }
        }
    }
}
