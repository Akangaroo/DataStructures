package cn.edu.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    public static void main(String[] args) {
        // 结点的个数
        int vertexSize = 8;
        // String[] vertexs = {"A", "B", "C", "D", "E"};
        String[] vertexs = {"1", "2", "3", "4", "5", "6", "7", "8"};
        // 创建图对象
        Graph graph = new Graph(vertexSize);
        // 添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        // graph.insertEdge(0, 1, 1);
        // graph.insertEdge(0, 2, 1);
        // graph.insertEdge(1, 2, 1);
        // graph.insertEdge(1, 3, 1);
        // graph.insertEdge(1, 4, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示邻接阵
        graph.showGraph();

        System.out.println("深度优先---");
        graph.dfs();
        System.out.println();
        System.out.println("广度优先---");
        graph.bfs();

    }

    private ArrayList<String> vertexList;// 存储顶点的集合
    private int[][] matrix;// 存储图对应的邻接矩阵
    private int numOfEdges;// 表示边的个数
    private boolean[] isVisited;// 表示结点是否被访问过


    /**
     * 初始化
     * @param vertexSize 结点的个数
     */
    public Graph(int vertexSize) {
        matrix = new int[vertexSize][vertexSize];
        vertexList = new ArrayList<>(vertexSize);
        numOfEdges = 0;
    }

    /**
     * 插入结点
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * @param v1    第一个结点
     * @param v2    第二个结点
     * @param weight    权重
     */
    public void insertEdge(int v1, int v2, int weight) {
        matrix[v1][v2] = weight;
        matrix[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 返回结点的个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 根据结点的索引拿到对应的值
     * @param index
     * @return
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 返回结点v1 和 v2对应的权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return matrix[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] edge : matrix) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 获取第一个邻接结点
     * @param index
     * @return  如果存在，返回结点对应的下标，如果不存在，返回-1；
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (matrix[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 找v1这一行的v2之后的邻接点
     * @param v1 代表的是矩阵中的i
     * @param v2 代表的是矩阵中的j
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (matrix[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 对一个结点进行深度优先遍历
     * @param i 代表的是当前结点，
     */
    private void dfs(int i) {
        System.out.print(getValueByIndex(i) + "\t");
        isVisited[i] = true;
        // 查找结点的第一个邻接结点
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明结点存在
            if (!isVisited[w]) {// 没有被访问过，递归调用
                dfs(w);
            }
            // 如果已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 对一个结点进行广度优先遍历
     * @param i
     */
    private void bfs(int i) {
        int u; // 表示队列的头节点对应的下标
        int w; // 表示邻接结点的w
        // 队列，记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        // 访问结点
        System.out.print(getValueByIndex(i) + "\t");
        isVisited[i] = true; //标记为已访问
        // 将结点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到第一个邻接点的下标
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    // 1. 打印
                    System.out.print(getValueByIndex(w) + "\t");
                    // 2. 标记为已访问
                    isVisited[w] = true;
                    // 3. 加入队列
                    queue.addLast(w);
                }
                // 找u这一行的w之后的邻接点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if(!isVisited[i]) {
                bfs(i);
            }
        }
    }
}
