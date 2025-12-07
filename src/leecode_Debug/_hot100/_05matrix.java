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
    /**
     *【难点在于顺序的问题，这几步顺序不能变】
     *【思路】 ①使用两个标记变量标记第一行和第一列是不是有0；
     *        ②使用第一行和第一列标记所有的元素对应的行列是不是有0.
     *    疑惑：为什么需要使用两个标记变量？？？
     *          答：②中会修改第一行或者第一列，避免对原来的数据造成污染
     *【易错点、写代码的关键】step1必须在step2之前。step3必须在step4之前并且step3中i和j必须都从1开始。
     *      如果step2在step1之前，就会导致原始数据的首行首列先被污染；再去统计首行首列的0的信息就
     *  错了。
     *      如果step4在step3之前，就会导致首行首列被修改，因此前面统计的信息也错了。
     */
    public void setZeroes(int[][] matrix) {
        boolean firRow = false,firCol = false;
        int m = matrix.length,n = matrix[0].length;
        /*step1：遍历第一行以及第一列，如果碰到0修改（第一行、第一列）对应的标志变量*/
        for (int i = 0; i < m; i++) {
            if (matrix[i][0]==0){
                firCol=true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i]==0){
                firRow = true;
                break;
            }
        }
        /*step2：遍历所有元素，如果matrix[i][j]为0，则将第一行以及第一列对应的元素置为0*/
        /**思考：下面的i,j都从0或者都从1开始，为什么都是对的？？*/
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j]==0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        /*step3：根据“第一行和第一列元素值”，修改除首行首列的所有元素值*/
        /**思考：下面的i,j为什么必须都从1开始，从0开始就错了？？？
         * 错误的用力如下————
                  输入
                  matrix =
                  [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
                  输出
                  [[0,0,0,0],[0,0,0,0],[0,0,0,0]]
                  预期结果
                  [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j] = 0;
            }
        }
        /*step4：根据标志变量的值，修改第一行和第一列的值*/
        if (firRow){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firCol){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }


    /*54.
    * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
    * */
    /**【思路】：四个变量标记边界，每一次打印一行(或者一列)，打印后需要更新top边界或者bottom边
     *      界(或者right或者left边界)，同时需要判断打印后是不是不满足条件了。
     * 【注意！！】
     *      1. 每一次for循环打印一行或者一列后，都要判断边界是不是合规，如果不合规需要就终止！！
     *   比如：打印完一行了，就要更新left/right的值，如果更新后left<=right的关系不成立了，就说
     *   明要终止了（即没有位置可以打印了）
     *      2. 本质上来说其实while的条件就是多余的，写成“while(true)”都没关系
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
//        if (matrix.length==0) return new int[0]; /**LCR 146与这个题是类似的，唯一的区别在于matrix可能为空，因此要判断一下*/
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (true) { /**err：这里写成"while(true)都没关系，真正关键在于“if判断语句的break”"*/
            /**
             * 合法打印必须是在每一行以及每一列打印完成后进行合法性校验！！！
             * 或者
             * 每一行以及每一列打印之前就行合法性校验
             */
            for (int i = left; i <= right; i++) { /**可以添加判断的位置2：也可以给整体的for循环外添加if条件判断*/
                res.add(matrix[top][i]);
            }
            if(++top>bottom) break; /**可以判断的位置1：在下一行或者下一列打印时，先判断是不是还有能打印的*/

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
        /*step1：沿着主对角线，对折矩阵*/
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        /**【注】err：每一行只用遍历到一半就可以，如果遍历完，就相当于交换正确了 但是又交换回去了，结果就错了
         * 下面j变量的变化范围要明确，只能到列维度的一半。。。
         *     否则如果写成“j<matrix[0].length”，运行的结果就是列没有变化。。因为：同一行中，研究前半
         * 部分的时候完成了交换，但是研究后半部分的时候又交换回去了。
         *     j位置的对称位置是哪里呢？如果j=0，对称位置是matrix[0].length-1；如果j=1，对称位置是matrix[0].length-2
         * 因此j位置的对称位置是 matrix[0].length-1-j
         * */
        /*step2：沿着矩阵竖直的中心线（即matrix[0].length/2）对称翻转*/
        for (int i=0;i<matrix.length;i++){ //每一行都有要交换的元素，因此i要从0变化到matrix.length
            for (int j=0;j<matrix[0].length/2;j++){ //每一行中，只用交换前一半就可以了。因此在"同一行中"，列只研究一半即"j<matrix[0].length/2"
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-1-j]; /**err：注意第1维度的索引是“martrix[0].length-1再减i”————即索引i位置的对称位置是matrix[0].length-1-i位置*/
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
        int n = matrix[0].length - 1;
        while (m < matrix.length && n >= 0) {
            int cur = matrix[m][n];
            if (cur == target) {
                return true;
            } else if (cur > target) {
                n--; //需要将cur变小，因此需要列变小
            } else {
                m++; //需要cur变大，因此需要将行变大
            }
        }
        return false;
    }
}
