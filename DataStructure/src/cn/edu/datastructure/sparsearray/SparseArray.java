package cn.edu.datastructure.sparsearray;

import java.io.*;

/**
 * 稀疏数组的转换
 */
public class SparseArray {
    public static void main(String[] args) {
        //1. 创建原始的二维数组
        int arr[][] = new int[11][11];
        arr[1][2] = 1;
        arr[2][3] = 2;

        //2. 输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int[] row : arr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //3. 遍历二维数组，得到非0的个数
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    sum++;
                }
            }
        }

        //4. 创建对应的二维数组
        int[][] sparseArr = new int[sum+1][3];
        //5. 给稀疏数组赋值
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = sum;

        //5. 遍历二维数组，将非0的值放到sparseArr中
        int count = 0;//用于记录是第几个非0数据
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(arr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }

        //6. 将稀疏数组保存到硬盘中
        try(FileWriter fw = new FileWriter("map.txt")){
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    fw.write(sparseArr[i][j]+"\t");
                }
                fw.write("\r\n");
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //7. 读出硬盘中的稀疏数组
        int[][] sparseArr2 = new int[sum+1][3];
        try(BufferedReader br = new BufferedReader(new FileReader("map.txt"))){
            String line = null;
            int row = 0;
            while((line = br.readLine())!=null){
                String[] temp = line.split("\t");
                for (int i = 0; i < temp.length; i++) {
                    sparseArr2[row][i] = Integer.parseInt(temp[i]);
                }
                row++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //8. 将稀疏数组恢复成原始数组
        int row = sparseArr2[0][0];
        int col = sparseArr2[0][1];
        int[][] arr2 = new int[row][col];

        for (int i = 1; i <= sparseArr2[0][2]; i++) {
            arr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        System.out.println("恢复后的的二维数组");
        for(int[] rows : arr){
            for(int data : rows){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
