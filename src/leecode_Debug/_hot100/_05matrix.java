package leecode_Debug._hot100;

import java.util.ArrayList;
import java.util.List;

public class _05matrix {

    public static void main(String[] args) {
        /*for循环在第一次执行的时候就会判断条件是不是成立，如果不成立则一次也不会执行*/
        for (int i=3;i<=2;i++){
            System.out.println("sod");
        }
    }


    /*73.
    * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和
    * 列的所有元素都设为 0 。请使用 原地 算法。
    * */
    public void setZeroes(int[][] matrix) {

    }


    /*54.
    * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
    * */
    /**【】：四个变量标记边界，每一次打印一行(或者一列)，打印后需要更新top边界或者bottom边
     * 界(或者right或者left边界)，同时需要判断打印后是不是不满足条件了。
     * 【注意！！】每一次for循环打印一行或者一列后，都要判断边界是不是合规，如果不合规需要
     * 终止*/
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (res.size() < matrix.length * matrix[0].length) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if(++top>bottom) break;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if(--right<left) break;
            for (int i = right; i >= left; i--) {
                res.add(matrix[bottom][i]);
            }
            if(--bottom<top) break;
            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if(++left>right) break;
        }
        return res;
    }


    /*48.
    * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
    你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。*/
    public void rotate(int[][] matrix) {
        for (int i=1;i<matrix.length;i++){
            for (int j=0;j<i;j++){
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i]= tmp;
            }
        }
        /**【注】
         * 下面j变量的变化范围要明确，只能到列维度的一半。。。
         *     否则如果写成“j<matrix[0].length”，运行的结果就是列没有变化。。因为：同一行中，研究前半
         * 部分的时候完成了交换，但是研究后半部分的时候又交换回去了。
         * */
        for (int i=0;i<matrix.length;i++){ //每一行都有要交换的元素，因此i要从0变化到matrix.length
            for (int j=0;j<matrix[0].length/2;j++){ //每一行中，只用交换前一半就可以了。因此在"同一行中"，列只研究一半即"j<matrix[0].length/2"
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-1-j];
                matrix[i][matrix[0].length-1-j] = tmp;
            }
        }
    }


    /*240.
    * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

    每行的元素从左到右升序排列。
    每列的元素从上到下升序排列。*/
    /**【关键的点】
     *      1. 重点就是从右上角开始比较。target大于当前位置就往下移动；target小于当前位置就往左移动
     *      2. 为什么从右上角比较，右上角的数相当于整个矩阵中的中位数.向左走不断变小，向右走不断变大
     * 【注】移动的过程中第一维是不断增大的，第二维是不断变小的。所以while的条件
     *      是"m<matrix.length&&n>=0"*/
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = 0;
        int n = matrix[0].length-1;
        while(m<matrix.length&&n>=0){
            int cur = matrix[m][n];
            if (cur==target){
                return true;
            }else if (cur>target){
                n--;
            }else {
                m++;
            }
        }
        return false;
    }
}
